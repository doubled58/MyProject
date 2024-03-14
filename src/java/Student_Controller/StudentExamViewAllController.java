package Student_Controller;

import DAL.ExamDAO;
import Models.Exam;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author ns
 */
@WebServlet(name = "StudentExamViewAllController", urlPatterns = {"/student/exam/all"})
public class StudentExamViewAllController extends HttpServlet {

    private static final String VIEW_PATH = "/views/student-exam-view.jsp";

    private final ExamDAO examDAO = new ExamDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Exam> exams = examDAO.getAllExams();
        request.setAttribute("exams", exams);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
