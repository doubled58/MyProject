package Admin_Controller;

import DAL.ExamDAO;
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
@WebServlet(name = "ExamUnregisterController", urlPatterns = {"/admin/exam/unregister"})
public class ExamUnregisterController extends HttpServlet {

    private final ExamDAO examDAO = new ExamDAO();
    
    private final ExamRegistedViewController examRegistedViewController = new ExamRegistedViewController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int examId = Integer.parseInt(request.getParameter("examId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        examDAO.unregisterExam(studentId, examId);
        request.setAttribute("message", "*Unregistered exam successfully!");
        examRegistedViewController.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
