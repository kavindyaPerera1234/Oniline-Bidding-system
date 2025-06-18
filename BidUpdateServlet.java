package onlineBiddingPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BidUpdateServlet")
public class BidUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid_id= request.getParameter("bid_id");
		String buyer_name= request.getParameter("buyer_name");
		String category= request.getParameter("category");
		String bid_date= request.getParameter("bid_date");
		double amount= Double.parseDouble(request.getParameter("amount"));
		String email= request.getParameter("email");
		String phone= request.getParameter("phone");
		
		boolean isTrue;
		isTrue = BidController.updatedata(bid_id, buyer_name, category, bid_date, amount,email,phone);
		
		if(isTrue ==true) {
			List<BiddingModel> BiddingDetails =BidController.getById(bid_id);
			request.setAttribute("BiddingDetails", BiddingDetails);
					
			String alertMessage = "Data Update Successful";
			response.getWriter().println("<script> alert('"+alertMessage+"');window.location.href='BidGetAllServlet'</script>");
		}
		else {
			RequestDispatcher dis2 = request.getRequestDispatcher("Bidwrong.jsp");
			dis2.forward(request, response);
		}
	}

}
