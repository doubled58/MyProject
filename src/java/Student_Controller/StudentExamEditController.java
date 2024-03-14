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
@WebServlet(name = "StudentExamEditController", urlPatterns = {"/student/exam/edit"})
public class StudentExamEditController extends HttpServlet {

    private static final String VIEW_PATH = "/views/student-exam-form.jsp";

    private final ExamDAO examDAO = new ExamDAO();

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

        int examId = Integer.parseInt(request.getParameter("examId"));
        Exam exam = examDAO.getStudentExam(userId, examId);
        if (exam == null) {
            response.sendRedirect(request.getContextPath() + "/student/exam/me");
            return;
        }

        request.setAttribute("examId", examId);
        request.setAttribute("note", exam.getStudentNote());
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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

        int examId = Integer.parseInt(request.getParameter("examId"));
        String note = request.getParameter("note");

        request.setAttribute("examId", examId);
        
        boolean result = examDAO.updateStudentExam(userId, examId, note);

        if (!result) {
            request.setAttribute("message", "*Edit student note failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }

        request.setAttribute("note", note);
        request.setAttribute("message", "*Edited student note successfully!");
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
