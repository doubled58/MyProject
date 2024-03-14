package Student_Controller;

import DAL.ExamDAO;
import Models.Exam;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author ns
 */
@WebServlet(name = "StudentExamController", urlPatterns = {"/student/exam/me"})
public class StudentExamController extends HttpServlet {

    private static final String VIEW_PATH = "/views/student-exam-view.jsp";

    private final ExamDAO examDAO = new ExamDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = null;
        if (session.getAttribute("user") != null) {
            user = (User)session.getAttribute("user"); 
        }
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        int userId = user.getId();
        
        request.setAttribute("isMe", true);
        ArrayList<Exam> exams = examDAO.getAllExamsByStudentId(userId);
        request.setAttribute("exams", exams);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
