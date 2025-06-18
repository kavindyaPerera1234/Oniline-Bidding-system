package feedback;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteFeedbackServlet")
public class DeleteFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fd_id = request.getParameter("fd_id");
        boolean isTrue;

        isTrue = FeedbackControl.deleteFeedback(fd_id);

        if (isTrue == true) {
            String alertMessage = "Feedback deleted successfully!";
            response.getWriter().println("<script>alert('" + alertMessage + "'); window.location.href='FeedbackGetAllServlet'</script>");
        } else {
            List<Feedback> feedbackList = FeedbackControl.getFeedbackById(fd_id);
            request.setAttribute("feedbackList", feedbackList);

            RequestDispatcher dispatcher = request.getRequestDispatcher("wrong.jsp");
            dispatcher.forward(request, response);
        }
    }
}
