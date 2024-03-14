/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin_Controller;

import DAL.ExamDAO;
import DAL.SlotDAO;
import DAL.SubjectDAO;
import Models.Exam;
import Models.Slot;
import Models.Subject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ExamAddController", urlPatterns = {"/admin/exam/add"})
public class ExamAddController extends HttpServlet {
   private static final String VIEW_PATH = "/views/exam-form.jsp";

    private final ExamDAO examDAO = new ExamDAO();

    private final SubjectDAO subjectDAO = new SubjectDAO();

    private final SlotDAO slotDAO = new SlotDAO();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ExamAddController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamAddController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 ArrayList<Slot> slots = slotDAO.getAllSlots();
        ArrayList<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("slots", slots);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
String name = request.getParameter("name");
        String subjectId = request.getParameter("subjectId");
        int slotId = Integer.parseInt(request.getParameter("slotId"));
        Date date = Date.valueOf(request.getParameter("date"));
        String room = request.getParameter("room");
        int maxSize = Integer.parseInt(request.getParameter("maxSize"));
        String note = request.getParameter("note");

        Exam exam = new Exam();
        exam.setName(name);
        exam.setSubjectId(subjectId);
        exam.setSlotId(slotId);
        exam.setDate(date);
        exam.setRoom(room);
        exam.setMaxSize(maxSize);
        exam.setNote(note);

        if (examDAO.checkIfExamAlreadyExisted(exam)) {
            request.setAttribute("message", "*Exam with subject " + subjectId + "at slot " + slotId + " - " + date + "is already existed!");
            doGet(request, response);
            return;
        }

        Exam addedExam = examDAO.addExam(exam);

        if (addedExam == null) {
            request.setAttribute("message", "*Add exam failed! Please check again!");
            doGet(request, response);
            return;
        }

        request.setAttribute("exam", exam);
        request.setAttribute("message", "*Added exam successfully!");
       
        doGet(request, response);
    }    

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
