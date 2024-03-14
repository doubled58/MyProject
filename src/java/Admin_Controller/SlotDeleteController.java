package Admin_Controller;

import DAL.SlotDAO;
import Models.Slot;
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
@WebServlet(name = "SlotDeleteController", urlPatterns = {"/admin/slot/delete"})
public class SlotDeleteController extends HttpServlet {

    private final SlotDAO slotDAO = new SlotDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("isEdit", true);
        int id = Integer.parseInt(request.getParameter("id"));
        Slot slot = slotDAO.getSlotById(id);
        if (slot != null) {
            slotDAO.deleteSlot(slot);
        }
        response.sendRedirect(request.getContextPath() + "/admin/slot");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
