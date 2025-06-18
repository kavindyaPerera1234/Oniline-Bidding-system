package UserPackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}


	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException { {


		UserModel user =(UserModel) request.getSession().getAttribute("user");
		
		if(user != null)
		{
			//forward user data to profie.jsp
			request.setAttribute("user", user);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		}else
		{
			//Handle case where user is not logged in
			response.sendRedirect("login.jsp");
		}
		
	}

	}
}
