package feedback;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetFeedbackByIdServlet")
public class GetFeedbackByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");  // Get feedback ID from request
        List<Feedback> feedbackList = FeedbackControl.getFeedbackById(id);  // Get list of feedback by ID
        request.setAttribute("feedbackList", feedbackList);  // Set as request attribute

        RequestDispatcher dispatcher = request.getRequestDispatcher("view-feedback.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
