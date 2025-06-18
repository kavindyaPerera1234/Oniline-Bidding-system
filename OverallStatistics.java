package delivery;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OverallStatistics")
public class OverallStatistics extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryCompanyDataAccess companyDAO;
    private DeliveryDataAccess deliveryDAO;
    
    public void init() {
        companyDAO = new DeliveryCompanyDataAccess();
        deliveryDAO = new DeliveryDataAccess();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get statistics
        int companyCount = companyDAO.countDeliveryCompanies();
        int deliveryCount = deliveryDAO.countDeliveries();
        
        // Set attributes for the JSP
        request.setAttribute("companyCount", companyCount);
        request.setAttribute("deliveryCount", deliveryCount);
        
        // Forward to the statistics JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("overallStatistics.jsp");
        dispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
} 