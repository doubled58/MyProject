package Admin_Controller;

import DAL.SubjectDAO;
import Models.Subject;
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
@WebServlet(name = "SubjectAddController", urlPatterns = {"/admin/subject/add"})
public class SubjectAddController extends HttpServlet {

    private static final String VIEW_PATH = "/views/subject-form.jsp";
    
    private static final String VIEW_PATH1 = "/views/subject-view.jsp";

    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Subject subject = new Subject(id, name);
        Subject addedSubject = subjectDAO.addSubject(subject);
        if (addedSubject == null || id.isEmpty() || name.isEmpty()) {
            request.setAttribute("message", "*Add subject failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }
        request.setAttribute("subject", subject);
        request.setAttribute("message", "*Added subject successfully!");
        ArrayList<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher(VIEW_PATH1).forward(request, response);
    }

}
