package feedback;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/UpdateFeedbackServlet")
public class UpdateFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      

        String fd_id         = request.getParameter("fd_id");
        String username      = request.getParameter("username");
        String role          = request.getParameter("role");
        String feedbackText  = request.getParameter("feedbackText");
        String feedbackDate  = request.getParameter("feedbackDate");
        String rate          = request.getParameter("rate");
        String replyMessage  = request.getParameter("replyMessage");

        boolean isTrue ;
        

            isTrue = FeedbackControl.updateFeedback(fd_id, username, role, feedbackText, feedbackDate, rate, replyMessage);

            if (isTrue == true) {
                List<Feedback> feedbackLists = FeedbackControl.getFeedbackById(fd_id); // Assuming this returns List
                request.setAttribute("feedbackLists", feedbackLists);

                String alertMessage = "Feedback Updated Successfully";
                response.getWriter().println("<script>alert('" + alertMessage + "'); window.location.href='FeedbackGetAllServlet'</script>");
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
                dispatcher.forward(request, response);
            }
        
        
       
        
}
}
