/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Role;
import Models.User;
import java.sql.Date;
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
public class UserDAO extends DBContext {

    public User login(String username, String password) {
        String sql = "select * from users where username = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int classId = rs.getInt("class_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date dob = rs.getDate("dob");
                    Role role = Role.valueOf(rs.getString("role"));
                    User user = new User();
                    user.setId(id);
                    user.setClassId(classId);
                    user.setUsername(username);
                    user.setName(name);
                    user.setEmail(email);
                    user.setDob(dob);
                    user.setRole(role);
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<User> getAllUsers() {
        String sql = "select * from users";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int classId = rs.getInt("class_id");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Date dob = rs.getDate("dob");
                Role role = Role.valueOf(rs.getString("role"));
                User user = new User();
                user.setId(id);
                user.setClassId(classId);
                user.setUsername(username);
                user.setName(name);
                user.setEmail(email);
                user.setDob(dob);
                user.setRole(role);
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUserById(int id) {
        String sql = "select * from users where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    int classId = rs.getInt("class_id");
                    String username = rs.getString("username");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date dob = rs.getDate("dob");
                    Role role = Role.valueOf(rs.getString("role"));
                    User user = new User();
                    user.setId(id);
                    user.setClassId(classId);
                    user.setUsername(username);
                    user.setName(name);
                    user.setEmail(email);
                    user.setDob(dob);
                    user.setRole(role);
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User register(User user) {
        String sql = "insert into users(class_id, username, password, name, email, dob, role) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getClassId());
            ps.setNString(2, user.getUsername());
            ps.setNString(3, user.getPassword());
            ps.setNString(4, user.getName());
            ps.setNString(5, user.getEmail());
            ps.setDate(6, (Date) user.getDob());
            ps.setString(7, user.getRole().toString());
            int re = ps.executeUpdate();
            if (re > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User updateUser(User user) {
        String sql = "update users set class_id = ?, username = ?, name  = ?, email = ?, dob = ?, role = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getClassId());
            ps.setNString(2, user.getUsername());
            ps.setNString(3, user.getName());
            ps.setNString(4, user.getEmail());
            ps.setDate(5, (Date) user.getDob());
            ps.setString(6, user.getRole().toString());
            ps.setInt(7, user.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User changePassword(User user) {
        String sql = "update users set password = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setNString(1, user.getPassword());
            ps.setInt(2, user.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User deleteUser(User user) {
        String sql = "delete from users where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   public static void main(String[] args) {
    UserDAO dao = new UserDAO();

    // Giả sử bạn đã có một đối tượng User từ cơ sở dữ liệu
    User userToUpdate = dao.getUserById(1);

    // Hiển thị mật khẩu hiện tại
    System.out.println("Current password: " + userToUpdate.getPassword());

    // Giả định bạn có một mật khẩu mới từ người dùng
    String newPassword = "1";

    // Thiết lập mật khẩu mới cho đối tượng User
    userToUpdate.setPassword(newPassword);

    // Thực hiện thay đổi mật khẩu
    User updatedUser = dao.changePassword(userToUpdate);

    // Kiểm tra xem thay đổi mật khẩu có thành công không
    if (updatedUser != null) {
        System.out.println("Password changed successfully!");
    } else {
        System.out.println("Failed to change password.");
    }

    // Hiển thị mật khẩu sau khi thay đổi
    System.out.println("Updated password: " + updatedUser.getPassword());
}

}
