package Admin_Controller;

import DAL.SlotDAO;
import Models.Slot;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import utils.TimeUtils;

/**
 *
 * @author ns
 */
@WebServlet(name = "SlotAddController", urlPatterns = {"/admin/slot/add"})
public class SlotAddController extends HttpServlet {

    private static final String VIEW_PATH = "/views/slot-form.jsp";

    private final SlotDAO slotDAO = new SlotDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        Time startTime = TimeUtils.getSqlTimeFromHTMLTimeInput(request.getParameter("startTime"));
        Time endTime = TimeUtils.getSqlTimeFromHTMLTimeInput(request.getParameter("endTime"));
        Slot slot = new Slot();
        slot.setName(name);
        slot.setStartTime(startTime);
        slot.setEndTime(endTime);
        Slot addedSlot = slotDAO.addSlot(slot);
        if (addedSlot == null) {
            request.setAttribute("message", "*Add slot failed!");
            request.getRequestDispatcher(VIEW_PATH).forward(request, response);
            return;
        }
        request.setAttribute("slot", slot);
        request.setAttribute("message", "*Added slot successfully!");
        request.getRequestDispatcher(VIEW_PATH).forward(request, response);
    }

}
