package delivery;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeliveryController")
public class DeliveryController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryDataAccess deliveryDAO;
    
    public void init() {
        deliveryDAO = new DeliveryDataAccess();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteDelivery(request, response);
                break;
            default:
                listDeliveries(request, response);
                break;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "insert":
                insertDelivery(request, response);
                break;
            case "update":
                updateDelivery(request, response);
                break;
            default:
                listDeliveries(request, response);
                break;
        }
    }
    
    private void listDeliveries(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Delivery> deliveries = deliveryDAO.getAllDeliveries();
        request.setAttribute("deliveries", deliveries);
        
        // Get company names for the dropdown
        List<String> companyNames = deliveryDAO.getAllDeliveryCompanyNames();
        request.setAttribute("companyNames", companyNames);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveries.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get company names for the dropdown
        List<String> companyNames = deliveryDAO.getAllDeliveryCompanyNames();
        request.setAttribute("companyNames", companyNames);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveries.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Delivery delivery = deliveryDAO.getDeliveryById(id);
        request.setAttribute("delivery", delivery);
        
        // Get company names for the dropdown
        List<String> companyNames = deliveryDAO.getAllDeliveryCompanyNames();
        request.setAttribute("companyNames", companyNames);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveries.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        String winnerName = request.getParameter("winnerName");
        String winnerAddress = request.getParameter("winnerAddress");
        String deliveryCompanyName = request.getParameter("deliveryCompanyName");
        
        Delivery delivery = new Delivery();
        delivery.setBidId(bidId);
        delivery.setWinnerName(winnerName);
        delivery.setWinnerAddress(winnerAddress);
        delivery.setDeliveryCompanyName(deliveryCompanyName);
        
        deliveryDAO.insertDelivery(delivery);
        response.sendRedirect("DeliveryController");
    }
    
    private void updateDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int bidId = Integer.parseInt(request.getParameter("bidId"));
        String winnerName = request.getParameter("winnerName");
        String winnerAddress = request.getParameter("winnerAddress");
        String deliveryCompanyName = request.getParameter("deliveryCompanyName");
        
        Delivery delivery = new Delivery();
        delivery.setId(id);
        delivery.setBidId(bidId);
        delivery.setWinnerName(winnerName);
        delivery.setWinnerAddress(winnerAddress);
        delivery.setDeliveryCompanyName(deliveryCompanyName);
        
        deliveryDAO.updateDelivery(delivery);
        response.sendRedirect("DeliveryController");
    }
    
    private void deleteDelivery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        deliveryDAO.deleteDelivery(id);
        response.sendRedirect("DeliveryController");
    }
} 