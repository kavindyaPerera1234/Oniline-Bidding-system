package delivery;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeliveryCompanyViewServlet")
public class DeliveryCompanyViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryCompanyDataAccess companyDAO;
       
    public void init() {
        companyDAO = new DeliveryCompanyDataAccess();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "view":
                viewCompanyDetails(request, response);
                break;
            case "list":
            default:
                listCompanies(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DeliveryCompany> companies = companyDAO.getAllDeliveryCompanies();
        request.setAttribute("companies", companies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewCompanies.jsp");
        dispatcher.forward(request, response);
    }
    
    private void viewCompanyDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeliveryCompany company = companyDAO.getDeliveryCompanyById(id);
        request.setAttribute("company", company);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewCompanyDetails.jsp");
        dispatcher.forward(request, response);
    }
}