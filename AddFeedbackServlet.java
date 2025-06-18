package feedback;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddFeedbackServlet")
public class AddFeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String feedbackText = request.getParameter("feedbackText");
        String feedbackDate = request.getParameter("feedbackDate");
        String rate = request.getParameter("rate");

        boolean isTrue = FeedbackControl.insertFeedback(username, role, feedbackText, feedbackDate, rate);

        if (isTrue == true) {
            String alertMessage = "Feedback Insert Successful!";
            response.getWriter().println("<script>alert('" + alertMessage + "');window.location.href='home.jsp'</script>");
        } else {
            String errorMessage = "Failed to add feedback.";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
