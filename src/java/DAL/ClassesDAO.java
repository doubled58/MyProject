/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Classes;
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
public class ClassesDAO extends DBContext{
    public ArrayList<Classes> getAllClasses() {
        String sql = "select * from classes";
        try ( PreparedStatement ps = connection.prepareStatement(sql);  ResultSet rs = ps.executeQuery();) {
            ArrayList<Classes> klasses = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Classes classes = new Classes(id, name);
                klasses.add(classes);
            }
            return klasses;
        } catch (SQLException ex) {
            Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Classes getClassesById(int id) {
        String sql = "select name from classes where id = ?";
        try { PreparedStatement ps = connection.prepareStatement(sql); 
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Classes klass = new Classes(id, name);
                    return klass;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Classes addClass(Classes classes) {
        String sql = "insert into classes(name) values (?)";
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, classes.getName());
            int re = ps.executeUpdate();
            if (re > 0) {
                return classes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Classes updateClass(Classes classes) {
        String sql = "update classes set name = ? where id = ?";
        try { PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, classes.getName());
            ps.setInt(2, classes.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return classes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Classes deleteClass(Classes classes) {
        String sql = "delete from classes where id = ?";
        try { PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, classes.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return classes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
