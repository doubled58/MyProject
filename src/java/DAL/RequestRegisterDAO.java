/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class RequestRegisterDAO extends DBContext {

    public ArrayList<RequestRegister> getAllRequestRegister() {
        String sql = "select * from registration_requests ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<RequestRegister> subjects = new ArrayList<>();
            while (rs.next()) {
                RequestRegister subject = new RequestRegister(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4));
                subject.setExam(new ExamDAO().getExamById(rs.getInt("exam_id")));
                subject.setU(new UserDAO().getUserById(rs.getInt("student_id")));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        for (RequestRegister arg : new RequestRegisterDAO().getAllRequestRegister()) {
            System.out.println(arg.isStatus());
        }
    }

    public boolean checkRegisted(String sid, String eid) {
        String sql = "select * from registration_requests where student_id = ? and exam_id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setString(1, sid);
            ps.setString(2, eid);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public void createRequest(String sid, String eid) {
        String sql = "INSERT INTO [dbo].[registration_requests]\n"
                + "           ([student_id] ,[exam_id],[status])\n"
                + "     VALUES  (?,?,0) ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, sid);
            ps.setString(2, eid);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeStatusRequest(String rid, String ss) {
        String sql = "UPDATE [dbo].[registration_requests]\n"
                + "   SET  [status] = ?  WHERE ID = " + rid;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, ss);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
