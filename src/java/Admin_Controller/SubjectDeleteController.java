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
@WebServlet(name = "SubjectDeleteController", urlPatterns = {"/admin/subject/delete"})
public class SubjectDeleteController extends HttpServlet {

    private final SubjectDAO subjectDAO = new SubjectDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isEdit", true);
        String id = request.getParameter("id");
        Subject subject = subjectDAO.getSubjectById(id);
        if (subject != null) {
            subjectDAO.deleteSubject(subject);
        }
        response.sendRedirect(request.getContextPath() + "/admin/subject");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
