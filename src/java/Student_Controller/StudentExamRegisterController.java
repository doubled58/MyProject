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

/**
 *
 * @author ns
 */
@WebServlet(name = "StudentExamRegisterController", urlPatterns = {"/student/exam/register"})
public class StudentExamRegisterController extends HttpServlet {

    private final ExamDAO examDAO = new ExamDAO();

    private final StudentExamViewAllController studentExamViewAllController = new StudentExamViewAllController();

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

        Exam exam = examDAO.getExamById(id);

        int examRegistedNumbers = examDAO.getExamRegistedNumbers(id);
        if (examRegistedNumbers != 0 && exam.getMaxSize() == examRegistedNumbers) {
            request.setAttribute("message", "*Register failed because exam room is full!");
            studentExamViewAllController.doGet(request, response);
            return;
        }

        examDAO.registerExam(userId, id, "");
        //request.setAttribute("message", "*Registered exam successfully!");
        
        studentExamController.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
