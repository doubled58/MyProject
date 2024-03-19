/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Dell
 */
public class RequestRegister {

    int ID;
    int student_id;
    int exam_id;
    boolean status;
    Exam exam;
    User u;

// pending
// confirmed
    public RequestRegister(int ID, int student_id, int exam_id, boolean status, Exam exam, User u) {
        this.ID = ID;
        this.student_id = student_id;
        this.exam_id = exam_id;
        this.status = status;
        this.exam = exam;
        this.u = u;
    }

    public RequestRegister(int ID, int student_id, int exam_id, boolean status) {
        this.ID = ID;
        this.student_id = student_id;
        this.exam_id = exam_id;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

}
