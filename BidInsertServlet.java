package onlineBiddingPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BidInsertServlet")
public class BidInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String buyer_name= request.getParameter("buyer_name");
		String category= request.getParameter("category");
		String bid_date= request.getParameter("bid_date");
		double amount= Double.parseDouble(request.getParameter("amount"));
		String email= request.getParameter("email");
		String phone= request.getParameter("phone");
		
		boolean isTrue;
		
		isTrue = BidController.insertdata(buyer_name, category, bid_date, amount, email, phone);
		
		if(isTrue ==true) {
			String alertMessage = "Data Insert Successful";
			response.getWriter().println("<script> alert('"+alertMessage+"');window.location.href='home.jsp'</script>");
		}
		else {
			RequestDispatcher dis2 = request.getRequestDispatcher("Bidwrong.jsp");
			dis2.forward(request, response);
		}
	}

}
