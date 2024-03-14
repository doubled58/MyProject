/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Loginout_controller;

import DAL.UserDAO;
import DAL.ClassesDAO;
import Models.Role;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AuthLoginController", urlPatterns = {"/auth/login"})

public class AuthLoginController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String VIEW_PATH = "/views/auth-login.jsp";

    private final ClassesDAO classDAO = new ClassesDAO();

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.login(username, password);

        if (user == null) {
            request.setAttribute("username", username);
            request.setAttribute("message", "*Login failed. Please check again!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

//        request.setAttribute("message", "*Loged in successfully!");
//        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
        if (user.getRole() == Role.ADMIN) {
            response.sendRedirect(request.getContextPath() + "/admin/exam");
        } else {
            response.sendRedirect(request.getContextPath() + "/student/exam/me");
        }

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
