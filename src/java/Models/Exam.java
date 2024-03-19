/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author Dell
 */
public class Exam {
    private int id;
    private int slotId;
    private String subjectId;
    private String name;
    private Date date;
    private String room;
    private String note;
    private int maxSize;
    private Slot slot;
    private Subject subject;
    private String studentNote;
    private boolean checkRegisted;
    public Exam() {
    }

    public Exam(int id, int slotId, String subjectId, String name, Date date, String room, String note, int maxSize, Slot slot, Subject subject, String studentNote) {
        this.id = id;
        this.slotId = slotId;
        this.subjectId = subjectId;
        this.name = name;
        this.date = date;
        this.room = room;
        this.note = note;
        this.maxSize = maxSize;
        this.slot = slot;
        this.subject = subject;
        this.studentNote = studentNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getStudentNote() {
        return studentNote;
    }

    public void setStudentNote(String studentNote) {
        this.studentNote = studentNote;
    }

    public boolean isCheckRegisted() {
        return checkRegisted;
    }

    public void setCheckRegisted(boolean checkRegisted) {
        this.checkRegisted = checkRegisted;
    }
    
    
}
