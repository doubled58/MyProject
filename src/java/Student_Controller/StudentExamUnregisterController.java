package Student_Controller;

import DAL.ExamDAO;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ns
 */
@WebServlet(name = "StudentExamUnregisterController", urlPatterns = {"/student/exam/unregister"})
public class StudentExamUnregisterController extends HttpServlet {

    private final ExamDAO examDAO = new ExamDAO();

    private final StudentExamController studentExamController = new StudentExamController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = null;
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/auth/login");
            return;
        }
        int userId = user.getId();
        int id = Integer.parseInt(request.getParameter("id"));
        examDAO.unregisterExam(userId, id);
        request.setAttribute("message", "*Unregistered exam successfully!");
        studentExamController.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
