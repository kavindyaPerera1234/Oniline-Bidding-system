package seller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ItemUpdateServlet")
public class ItemUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		    String item_id     = request.getParameter("item_id");
			String seller_name = request.getParameter("seller_name");
			String item_title  = request.getParameter("item_title");
			String category    = request.getParameter("category");
			String price       = request.getParameter("price");
			String start_time  = request.getParameter("start_time");
			String end_time    = request.getParameter("end_time");
			String description = request.getParameter("description");
			
			boolean isTrue;
			isTrue = ItemControl.updatedata(item_id, seller_name, item_title, category, price, start_time, end_time, description);
	
			if(isTrue == true)
			{
				List <ItemModel> itemDetails = ItemControl.getById(item_id);
				request.setAttribute("itemDetails", itemDetails);
				
				String alertMessage ="Data Update Successful";
				response.getWriter().println("<script>alert('"+alertMessage+"');window.location.href='ItemGetAllServlet'</script>");
			}
			else {
				
				RequestDispatcher dis2= request.getRequestDispatcher("wrong.jsp");
				dis2.forward(request, response);
			}
	}

}
