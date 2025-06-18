package onlineBiddingPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BidDeleteServlet")
public class BidDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bid_id = request.getParameter("bid_id");
		boolean isTrue;
		isTrue= BidController.deletedata(bid_id);
		if(isTrue == true) {
			String alertMessage = "Data Delete Successful";
			response.getWriter().println("<script>alert('"+alertMessage+"');"+"window.location.href='BidGetAllServlet';</script>");
		}
		else {
			List<BiddingModel> BiddingDetails =BidController.getById(bid_id);
			request.setAttribute("BiddingDetails",BiddingDetails);
			
			RequestDispatcher dispacher = request.getRequestDispatcher("Bidwrong.jsp");
			dispacher.forward(request,response);
		}
	}

}
