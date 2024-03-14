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
@WebServlet(name = "SubjectViewController", urlPatterns = {"/admin/subject"})
public class SubjectViewController extends HttpServlet {

    private static final String VIEW_PATH = "/views/subject-view.jsp";

    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Subject> subjects = subjectDAO.getAllSubjects();
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
