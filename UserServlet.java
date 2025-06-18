package UserPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.ItemControl;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Get data from form
		
		String role = request.getParameter("role");
		String name = request.getParameter("name");
		String gmail = request.getParameter("gmail");
		String nic= request.getParameter("nic");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		boolean isTrue;
		
		isTrue = UserControl.insertdata(role, name, gmail, nic, address, phone, username, password);
		
		if(isTrue)
		{
			
			String alertMessage ="Register Successful";
			response.getWriter().println("<script>alert('"+alertMessage+"');window.location.href='login.jsp'</script>");
		}
		else {
			
			RequestDispatcher dis2= request.getRequestDispatcher("wrong.jsp");
			dis2.forward(request, response);
		}
	}
	

}
