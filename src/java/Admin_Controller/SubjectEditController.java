package Admin_Controller;

import DAL.SubjectDAO;
import Models.Subject;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ns
 */
@WebServlet(name = "SubjectEditController", urlPatterns = {"/admin/subject/edit"})
public class SubjectEditController extends HttpServlet {

    private static final String VIEW_PATH = "/views/subject-form.jsp";

    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isEdit", true);
        String id = request.getParameter("id");
        Subject subject = subjectDAO.getSubjectById(id);
        if (subject == null) {
            response.sendRedirect(request.getContextPath() + "/admin/subject");
            return;
        }
        request.setAttribute("subject", subject);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isEdit", true);
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        Subject updatedSubject = subjectDAO.updateSubject(subject);
        if (updatedSubject == null) {
            request.setAttribute("message", "*Edit subject failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }
        request.setAttribute("subject", subject);
        request.setAttribute("message", "*Edited subject successfully!");
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
