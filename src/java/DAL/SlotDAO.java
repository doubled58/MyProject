/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Slot;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class SlotDAO extends DBContext{
    
    public ArrayList<Slot> getAllSlots() {
        String sql = "select * from slots";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Slot> slots = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");
                Slot slot = new Slot(id, name, startTime, endTime);
                slots.add(slot);
            }
            return slots;
        } catch (SQLException ex) {
            Logger.getLogger(SlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Slot getSlotById(int id) {
        String sql = "select * from slots where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    Slot slot = new Slot(id, name, startTime, endTime);
                    return slot;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Slot addSlot(Slot slot) {
        String sql = "insert into slots(name, start_time, end_time) values (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, slot.getName());
            ps.setTime(2, slot.getStartTime());
            ps.setTime(3, slot.getEndTime());
            int re = ps.executeUpdate();
            if (re > 0) {
                return slot;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Slot updateSlot(Slot slot) {
        String sql = "update slots set name = ?, start_time = ?, end_time = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, slot.getName());
            ps.setTime(2, slot.getStartTime());
            ps.setTime(3, slot.getEndTime());
            ps.setInt(4, slot.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return slot;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Slot deleteSlot(Slot slot) {
        String sql = "delete from slots where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, slot.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return slot;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public static void main(String[] args) {
        SlotDAO slotDAO = new SlotDAO();

        // Tạo một đối tượng Slot mới
        Slot newSlot = new Slot(1, "Morning", Time.valueOf("08:00:00"), Time.valueOf("12:00:00"));

        // Thêm đối tượng Slot mới vào cơ sở dữ liệu
        Slot addedSlot = slotDAO.addSlot(newSlot);

        if (addedSlot != null) {
            System.out.println("Slot added successfully!");
            System.out.println("ID: " + addedSlot.getId());
            System.out.println("Name: " + addedSlot.getName());
            System.out.println("Start Time: " + addedSlot.getStartTime());
            System.out.println("End Time: " + addedSlot.getEndTime());
        } else {
            System.out.println("Failed to add slot!");
        }
    }
}
