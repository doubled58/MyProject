/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin_Controller;

import DAL.ClassesDAO;
import Models.Classes;
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
@WebServlet(name = "ClassEditController", urlPatterns = {"/admin/class/edit"})
public class ClassEditController extends HttpServlet {
   
     private static final String VIEW_PATH = "/views/class-form.jsp";

    private final ClassesDAO classesDAO = new ClassesDAO();
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
            out.println("<title>Servlet ClassAddController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClassAddController at " + request.getContextPath () + "</h1>");
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
        request.setAttribute("isEdit", true);
        int id = Integer.parseInt(request.getParameter("id"));
        Classes classes = classesDAO.getClassesById(id);
        if (classes == null) {
            response.sendRedirect(request.getContextPath() + "/admin/class");
            return;
        }
        request.setAttribute("klass", classes);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setAttribute("isEdit", true);
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Classes classes = new Classes();
        classes.setId(id);
        classes.setName(name);
        Classes addedKlass = classesDAO.updateClass(classes);
        if (addedKlass == null) {
            request.setAttribute("message", "*Edit class failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }
        request.setAttribute("klass", classes);
        request.setAttribute("message", "*Edited class successfully!");
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
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
