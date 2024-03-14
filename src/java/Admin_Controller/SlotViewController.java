package Admin_Controller;

import DAL.SlotDAO;
import Models.Slot;
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
@WebServlet(name = "SlotViewController", urlPatterns = {"/admin/slot"})
public class SlotViewController extends HttpServlet {

    private static final String VIEW_PATH = "/views/slot-view.jsp";

    private final SlotDAO slotDAO = new SlotDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Slot> slots = slotDAO.getAllSlots();
        request.setAttribute("slots", slots);
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
