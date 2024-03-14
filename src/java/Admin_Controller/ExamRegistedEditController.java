/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Admin_Controller;

import DAL.ExamDAO;
import Models.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ExamRegistedEditController", urlPatterns = {"/admin/exam/registed/edit"})
public class ExamRegistedEditController extends HttpServlet {

    private static final String VIEW_PATH = "/views/exam-registed-form.jsp";

    private final ExamDAO examDAO = new ExamDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet ExamRegistedEditController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExamRegistedEditController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        request.setAttribute("examId", examId);

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        request.setAttribute("studentId", studentId);

        Exam exam = examDAO.getStudentExam(studentId, examId);
        if (exam == null) {
            response.sendRedirect(request.getContextPath() + "/admin/exam/registed");
            return;
        }

        request.setAttribute("note", exam.getStudentNote());
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        request.setAttribute("examId", examId);

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        request.setAttribute("studentId", studentId);

        String note = request.getParameter("note");
        request.setAttribute("note", note);

        boolean result = examDAO.updateStudentExam(studentId, examId, note);

        if (!result) {
            request.setAttribute("message", "*Edit student note failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }

        request.setAttribute("message", "*Edited student note successfully!");
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
