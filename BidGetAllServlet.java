package onlineBiddingPackage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BidGetAllServlet")
public class BidGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	 List <BiddingModel>allBiddings = BidController.getAllBid();
    	 request.setAttribute("allBidding", allBiddings);
    	 
    	 RequestDispatcher dispatcher = request.getRequestDispatcher("BidDisplay.jsp");
    	 dispatcher.forward(request, response);
    }
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	doGet(request,response);
	 
	}
}