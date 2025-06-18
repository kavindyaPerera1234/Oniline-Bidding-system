package UserPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String id = request.getParameter("id");
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String gmail = request.getParameter("gmail");
		String nic= request.getParameter("nic");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		boolean isTrue;
		isTrue = UserControl.updateProfile(id, role, name, gmail, nic, address, phone, username, password);

		if(isTrue == true)
		{
			List <UserModel> user = UserControl.userProfile(role,id);
			request.setAttribute("user", user.get(0));
			
			String alertMessage ="Data Update Successful";
			response.getWriter().println("<script>alert('"+alertMessage+"');window.location.href='ProfileServlet'</script>");
			
		}
		else {
			
			RequestDispatcher dis2= request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}

	}

}
