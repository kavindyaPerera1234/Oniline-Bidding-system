package UserPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String role = request.getParameter("role"); 

	    try {
	        List<UserModel> userlogin = null;

	        if (role != null && !role.isEmpty()) {
	            // Buyer or Seller login
	            userlogin = UserControl.loginValidate(username, password, role);
	            if (userlogin != null && !userlogin.isEmpty()) {
	                request.getSession().setAttribute("user", userlogin.get(0));
	                response.sendRedirect("home.jsp");
	                return;
	            }
	        } else {
	            // Admin or Manager login (check both)
	            boolean isAdmin = UserControl.validateAdmin(username, password);
	            boolean isManager = UserControl.validateManager(username, password);

	            if (isAdmin) {
	                request.getSession().setAttribute("admin", username);
	                response.sendRedirect("AdminDashboard.jsp");
	                return;
	            } else if (isManager) {
	                request.getSession().setAttribute("manager", username);
	                response.sendRedirect("ManagerDashboard.jsp");
	                return;
	            }
	        }

	        // If login failed
	        response.getWriter().println("<script>alert('Invalid credentials. Please try again.'); window.location.href='login.jsp';</script>");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}
