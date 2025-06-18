package delivery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/DeliveryCompanyController")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 5 * 5)

public class DeliveryCompanyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DeliveryCompanyDataAccess companyDAO;
    private static final String UPLOAD_DIRECTORY = "company_logos";
    
    public void init() {
        companyDAO = new DeliveryCompanyDataAccess();
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
                deleteCompany(request, response);
                break;
            default:
                listCompanies(request, response);
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
                insertCompany(request, response);
                break;
            case "update":
                updateCompany(request, response);
                break;
            default:
                listCompanies(request, response);
                break;
        }
    }
    
    private void listCompanies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DeliveryCompany> companies = companyDAO.getAllDeliveryCompanies();
        request.setAttribute("companies", companies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveryCompany.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveryCompany.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DeliveryCompany company = companyDAO.getDeliveryCompanyById(id);
        request.setAttribute("company", company);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageDeliveryCompany.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        // Process file upload
        String logoPath = "";
        Part filePart = request.getPart("logo");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            String finalPath = uploadPath + File.separator + fileName;
            filePart.write(finalPath);
            logoPath = UPLOAD_DIRECTORY + File.separator + fileName;
        }
        
        DeliveryCompany company = new DeliveryCompany();
        company.setName(name);
        company.setAddress(address);
        company.setPhoneNumber(phoneNumber);
        company.setLogo(logoPath);
        company.setEmail(email);
        
        companyDAO.insertDeliveryCompany(company);
        response.sendRedirect("DeliveryCompanyController");
    }
    
    private void updateCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        
        DeliveryCompany company = companyDAO.getDeliveryCompanyById(id);
        String logoPath = company.getLogo(); // Keep existing logo path
        
        // Process file upload if a new file is provided
        Part filePart = request.getPart("logo");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = getFileName(filePart);
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + UPLOAD_DIRECTORY;
            
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            String finalPath = uploadPath + File.separator + fileName;
            filePart.write(finalPath);
            logoPath = UPLOAD_DIRECTORY + File.separator + fileName;
        }
        
        company.setName(name);
        company.setAddress(address);
        company.setPhoneNumber(phoneNumber);
        company.setLogo(logoPath);
        company.setEmail(email);
        
        companyDAO.updateDeliveryCompany(company);
        response.sendRedirect("DeliveryCompanyController");
    }
    
    private void deleteCompany(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        companyDAO.deleteDeliveryCompany(id);
        response.sendRedirect("DeliveryCompanyController");
    }
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}