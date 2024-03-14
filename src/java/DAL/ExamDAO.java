/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Classes;
import Models.Exam;
import Models.Slot;
import Models.Subject;
import Models.User;
import java.sql.Date;
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
public class ExamDAO extends DBContext {

    public boolean checkIfExamAlreadyExisted(Exam exam) {
        String sql = "select id from exams where slot_id = ? and subject_id = ? and date = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exam.getSlotId());
            ps.setString(2, exam.getSubjectId());
            ps.setDate(3, (Date) exam.getDate());
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Exam> getAllExams() {
        String sql = "select exams.id as id, slot_id, subject_id, exams.name as name, date, room, note, max_size, \n"
                + "slots.name as slot_name, start_time, end_time, \n"
                + "subject_id, subjects.name as subject_name from exams \n"
                + "join slots on slots.id = exams.slot_id \n"
                + "join subjects on subjects.id = exams.subject_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<Exam> exams = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                int slotId = rs.getInt("slot_id");
                String subjectId = rs.getString("subject_id");
                String name = rs.getString("name");
                Date date = rs.getDate("date");
                String room = rs.getString("room");
                String note = rs.getString("note");
                int maxSize = rs.getInt("max_size");
                Exam exam = new Exam();
                exam.setId(id);
                exam.setSlotId(slotId);
                exam.setSubjectId(subjectId);
                exam.setName(name);
                exam.setDate(date);
                exam.setRoom(room);
                exam.setNote(note);
                exam.setMaxSize(maxSize);

                String slotName = rs.getString("slot_name");
                Time startTime = rs.getTime("start_time");
                Time endTime = rs.getTime("end_time");
                Slot slot = new Slot(slotId, slotName, startTime, endTime);
                exam.setSlot(slot);

                String subjectName = rs.getString("subject_name");
                Subject subject = new Subject(subjectId, subjectName);
                exam.setSubject(subject);

                exams.add(exam);
            }
            return exams;
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Exam> getAllExamsByStudentId(int studentId) {
        String sql = "select exams.id as id, slot_id, subject_id, exams.name as name, date, room, exams.note as note, max_size,\n"
                + "slots.name as slot_name, start_time, end_time,\n"
                + "subject_id, subjects.name as subject_name,\n"
                + "exam_students.note as student_note\n"
                + "from exam_students\n"
                + "join exams on exams.id = exam_students.exam_id and exam_students.student_id = ?\n"
                + "join slots on slots.id = exams.slot_id\n"
                + "join subjects on subjects.id = exams.subject_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery();) {
                ArrayList<Exam> exams = new ArrayList<>();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int slotId = rs.getInt("slot_id");
                    String subjectId = rs.getString("subject_id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("date");
                    String room = rs.getString("room");
                    String note = rs.getString("note");
                    int maxSize = rs.getInt("max_size");

                    Exam exam = new Exam();
                    exam.setId(id);
                    exam.setSlotId(slotId);
                    exam.setSubjectId(subjectId);
                    exam.setName(name);
                    exam.setDate(date);
                    exam.setRoom(room);
                    exam.setNote(note);
                    exam.setMaxSize(maxSize);

                    String slotName = rs.getString("slot_name");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    Slot slot = new Slot(slotId, slotName, startTime, endTime);
                    exam.setSlot(slot);

                    String subjectName = rs.getString("subject_name");
                    Subject subject = new Subject(subjectId, subjectName);
                    exam.setSubject(subject);

                    String studentNote = rs.getString("student_note");
                    exam.setStudentNote(studentNote);

                    exams.add(exam);
                }
                return exams;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam getStudentExam(int studentId, int examId) {
        String sql = "select exams.id as id, slot_id, subject_id, exams.name as name, date, room, exams.note as note, max_size,\n"
                + "slots.name as slot_name, start_time, end_time,\n"
                + "subject_id, subjects.name as subject_name,\n"
                + "exam_students.note as student_note\n"
                + "from exam_students\n"
                + "join exams on exams.id = exam_students.exam_id and exam_students.student_id = ? and exam_students.exam_id = ? \n"
                + "join slots on slots.id = exams.slot_id\n"
                + "join subjects on subjects.id = exams.subject_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, studentId);
            ps.setInt(2, examId);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int slotId = rs.getInt("slot_id");
                    String subjectId = rs.getString("subject_id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("date");
                    String room = rs.getString("room");
                    String note = rs.getString("note");
                    int maxSize = rs.getInt("max_size");

                    Exam exam = new Exam();
                    exam.setId(id);
                    exam.setSlotId(slotId);
                    exam.setSubjectId(subjectId);
                    exam.setName(name);
                    exam.setDate(date);
                    exam.setRoom(room);
                    exam.setNote(note);
                    exam.setMaxSize(maxSize);

                    String slotName = rs.getString("slot_name");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    Slot slot = new Slot(slotId, slotName, startTime, endTime);
                    exam.setSlot(slot);

                    String subjectName = rs.getString("subject_name");
                    Subject subject = new Subject(subjectId, subjectName);
                    exam.setSubject(subject);

                    String studentNote = rs.getString("student_note");
                    exam.setStudentNote(studentNote);

                    return exam;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam getExamById(int id) {
        String sql = "select exams.id as id, slot_id, subject_id, exams.name as name, date, room, note, max_size, \n"
                + "slots.name as slot_name, start_time, end_time, \n"
                + "subject_id, subjects.name as subject_name from exams \n"
                + "join slots on slots.id = exams.slot_id and exams.id = ? \n"
                + "join subjects on subjects.id = exams.subject_id";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    int slotId = rs.getInt("slot_id");
                    String subjectId = rs.getString("subject_id");
                    String name = rs.getString("name");
                    Date date = rs.getDate("date");
                    String room = rs.getString("room");
                    String note = rs.getString("note");
                    int maxSize = rs.getInt("max_size");
                    Exam exam = new Exam();
                    exam.setId(id);
                    exam.setSlotId(slotId);
                    exam.setSubjectId(subjectId);
                    exam.setName(name);
                    exam.setDate(date);
                    exam.setRoom(room);
                    exam.setNote(note);
                    exam.setMaxSize(maxSize);

                    String slotName = rs.getString("slot_name");
                    Time startTime = rs.getTime("start_time");
                    Time endTime = rs.getTime("end_time");
                    Slot slot = new Slot(slotId, slotName, startTime, endTime);
                    exam.setSlot(slot);

                    String subjectName = rs.getString("subject_name");
                    Subject subject = new Subject(subjectId, subjectName);
                    exam.setSubject(subject);

                    return exam;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam addExam(Exam exam) {
        String sql = "insert into exams(slot_id, subject_id, name, date, room, note, max_size) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exam.getSlotId());
            ps.setString(2, exam.getSubjectId());
            ps.setString(3, exam.getName());
            ps.setDate(4, (Date) exam.getDate());
            ps.setString(5, exam.getRoom());
            ps.setString(6, exam.getNote());
            ps.setInt(7, exam.getMaxSize());
            int re = ps.executeUpdate();
            if (re > 0) {
                return exam;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam updateExam(Exam exam) {
        String sql = "update exams set slot_id = ?, subject_id = ?, name = ?, date = ?, room = ?, note = ?, max_size = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exam.getSlotId());
            ps.setString(2, exam.getSubjectId());
            ps.setString(3, exam.getName());
            ps.setDate(4, (Date) exam.getDate());
            ps.setString(5, exam.getRoom());
            ps.setString(6, exam.getNote());
            ps.setInt(7, exam.getMaxSize());
            ps.setInt(8, exam.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return exam;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam deleteExam(Exam exam) {
        String sql = "delete from exams where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, exam.getId());
            int re = ps.executeUpdate();
            if (re > 0) {
                return exam;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean registerExam(int studentId, int examId, String note) {
        String sql = "insert into exam_students(exam_id, student_id, note) values (?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            ps.setInt(2, studentId);
            ps.setString(3, note);
            int re = ps.executeUpdate();
            if (re > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean unregisterExam(int studentId, int examId) {
        String sql = "delete from exam_students where exam_id = ? and student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, examId);
            ps.setInt(2, studentId);
            int re = ps.executeUpdate();
            if (re > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<User> getExamStudentList(int examId) {
        String sql = "select student_id, users.username as student_username, users.name as student_name, users.email as student_email, \n"
                + "users.dob as student_dob, classes.id as class_id, classes.name as class_name, \n"
                + "exam_students.note as student_note from exam_students\n"
                + "join exams on exams.id = exam_students.exam_id and exam_students.exam_id = ?\n"
                + "join users on users.id = exam_students.student_id\n"
                + "join classes on classes.id = users.class_id";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery();) {
                ArrayList<User> students = new ArrayList<>();
                while (rs.next()) {
                    int studentId = rs.getInt("student_id");
                    String studentUsername = rs.getString("student_username");
                    String studentName = rs.getString("student_name");
                    String studentEmail = rs.getString("student_email");
                    Date studentDob = rs.getDate("student_dob");

                    int classId = rs.getInt("class_id");
                    String className = rs.getString("class_name");
                    Classes classes = new Classes(classId, className);

                    User student = new User();
                    student.setClassId(classId);
                    student.setDob(studentDob);
                    student.setEmail(studentEmail);
                    student.setId(studentId);
                    student.setClasses(classes);
                    student.setName(studentName);
                    student.setUsername(studentUsername);

                    String studentNote = rs.getString("student_note");
                    student.setStudentNote(studentNote);

                    students.add(student);
                }
                return students;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateStudentExam(int studentId, int examId, String note) {
        String sql = "update exam_students set note = ? where student_id = ? and exam_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, note);
            ps.setInt(2, studentId);
            ps.setInt(3, examId);
            int re = ps.executeUpdate();
            if (re > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getExamRegistedNumbers(int examId) {
        String sql = "select count(student_id) as total from exam_students where exam_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    return total;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void main(String[] args) {
        ExamDAO examDAO = new ExamDAO();

        // Gọi phương thức getAllExams để lấy danh sách tất cả các kỳ thi từ cơ sở dữ liệu
        ArrayList<Exam> allExams = examDAO.getAllExams();

        if (allExams != null) {
            System.out.println("List of all exams:");
            for (Exam exam : allExams) {
                System.out.println("ID: " + exam.getId());
                System.out.println("Slot ID: " + exam.getSlotId());
                System.out.println("Subject ID: " + exam.getSubjectId());
                System.out.println("Name: " + exam.getName());
                System.out.println("Date: " + exam.getDate());
                System.out.println("Room: " + exam.getRoom());
                System.out.println("Note: " + exam.getNote());
                System.out.println("Max Size: " + exam.getMaxSize());

                Slot slot = exam.getSlot();
                System.out.println("Slot Info:");
                System.out.println("\tID: " + slot.getId());
                System.out.println("\tName: " + slot.getName());
                System.out.println("\tStart Time: " + slot.getStartTime());
                System.out.println("\tEnd Time: " + slot.getEndTime());

                Subject subject = exam.getSubject();
                System.out.println("Subject Info:");
                System.out.println("\tID: " + subject.getId());
                System.out.println("\tName: " + subject.getName());

                System.out.println("---------------------------------------");
            }
        } else {
            System.out.println("Failed to retrieve exams from the database!");
        }
    }
}
