/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Loginout_controller;

import DAL.ClassesDAO;
import DAL.UserDAO;
import Models.Classes;
import Models.Role;
import Models.User;
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
@WebServlet(name = "AuthRegisterController", urlPatterns = {"/auth/register"})
public class AuthRegisterController extends HttpServlet {
   
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
            out.println("<title>Servlet AuthRegisterController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthRegisterController at " + request.getContextPath () + "</h1>");
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
    private static final String VIEW_PATH = "/views/auth-register.jsp";

    private final ClassesDAO classesDAO = new ClassesDAO();

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Classes> classes = classesDAO.getAllClasses();
        request.setAttribute("klasses", classes);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int classId = Integer.parseInt(request.getParameter("classId"));
        String name = request.getParameter("name");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setClassId(classId);
        user.setDob(dob);
        user.setEmail(email);
        user.setName(name);
        user.setUsername(username);
        user.setRole(Role.STUDENT);

        user.setPassword(password);

        User registedUser = userDAO.register(user);
        
        if (registedUser == null) {
            user.setPassword("");
            request.setAttribute("user", user);
            request.setAttribute("message", "*Register failed. Please check again!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }

        request.setAttribute("message", "*Registered successfully. Please go to log-in!");
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
