/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Subject;
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
public class SubjectDAO extends DBContext {

    public ArrayList<Subject> getAllSubjects() {
        String sql = "select * from subjects";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getNString("name");
                Subject subject = new Subject(id, name);
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Subject getSubjectById(String id) {
        String sql = "select name from subjects where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    String name = rs.getNString("name");
                    Subject subject = new Subject(id, name);
                    return subject;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Subject addSubject(Subject subject) {
        String sql = "insert into subjects(id, name) values (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getId());
            ps.setNString(2, subject.getName());
            int re = ps.executeUpdate();
            if (re > 0) {
                return subject;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Subject updateSubject(Subject subject) {
        String sql = "update subjects set name = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getName());
            ps.setNString(2, subject.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return subject;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Subject deleteSubject(Subject subject) {
        String sql = "delete from subjects where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, subject.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return subject;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        SubjectDAO subjectDAO = new SubjectDAO();

        // Tạo một đối tượng Subject mới
        Subject newSubject = new Subject("1", "Mathematics");

        // Thêm đối tượng Subject mới vào cơ sở dữ liệu
        Subject addedSubject = subjectDAO.addSubject(newSubject);

        if (addedSubject != null) {
            System.out.println("Subject added successfully!");
            System.out.println("ID: " + addedSubject.getId());
            System.out.println("Name: " + addedSubject.getName());
        } else {
            System.out.println("Failed to add subject!");
        }
    }


}
