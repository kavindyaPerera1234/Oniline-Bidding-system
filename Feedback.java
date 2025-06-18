package feedback;

public class Feedback {
    private int fd_id;
    private String username;
    private String role;
    private String feedbackText;
    private String feedbackDate;
    private String rate;
    private String replyMessage;  // Added the replyMessage field
    
    // Constructor with the new replyMessage field
    public Feedback(int fd_id, String username, String role, String feedbackText, String feedbackDate, String rate, String replyMessage) {
        this.fd_id = fd_id;
        this.username = username;
        this.role = role;
        this.feedbackText = feedbackText;
        this.feedbackDate = feedbackDate;
        this.rate = rate;
        this.replyMessage = replyMessage;
    }

    // Getter and Setter for replyMessage
    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }

    // Getter and Setter for fd_id
    public int getFd_id() {
        return fd_id;
    }

    public void setFd_id(int fd_id) {
        this.fd_id = fd_id;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Getter and Setter for feedbackText
    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    // Getter and Setter for feedbackDate
    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    // Getter and Setter for rate
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
