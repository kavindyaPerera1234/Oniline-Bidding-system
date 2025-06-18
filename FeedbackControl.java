package feedback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class FeedbackControl {
	   
		private static boolean isSuccess;
		private static Connection con = null;
		private static Statement stmt = null;
		private static ResultSet rs = null;
		
    
    // Method to insert new feedback
	public static boolean insertFeedback(String username, String role, String feedbackText, String feedbackDate, String rate) {
	    boolean isSuccess = false;

	    try {
	         con = DBConnection.getConnection();
	         stmt = con.createStatement();

	        String sql = "INSERT INTO feedback VALUES (0, '" + username + "', '" + role + "', '" + feedbackText + "', '" + feedbackDate + "', '" + rate + "', '')";
	        int rs = stmt.executeUpdate(sql);

	        if (rs > 0) {
	            isSuccess = true;
	        } else {
	            isSuccess = false;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return isSuccess;
	}

    
 // Method to fetch feedback by its ID
	public static List<Feedback> getFeedbackById(String id) {
		
		
	    int convertedID = Integer.parseInt(id);
	    ArrayList<Feedback> feedbackList = new ArrayList<>();

	    try  {
	    	 con = DBConnection.getConnection();
	    	 stmt= con.createStatement();
	    	 
	        String sql = "SELECT * FROM feedback WHERE fd_id = '"+convertedID+"'";
	         rs = stmt.executeQuery(sql);

	        while (rs.next()) {
	        	int fd_id         = rs.getInt(1);
			    String username   = rs.getString(2);
			    String role       = rs.getString(3);
			    String feedbackText   = rs.getString(4);
			    String feedbackDate   = rs.getString(5);
			    String rate           = rs.getString(6);
			    String replyMessage   = rs.getString(7);
			    
			      
	   
			    Feedback  fb = new Feedback(fd_id,username,role,feedbackText,feedbackDate,rate,replyMessage);
			    feedbackList.add(fb);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return feedbackList;  // Return the list of feedback (could be empty if not found)
	}



    // Method to get all feedback
    public static List<Feedback> getAllFeedback() {
        List<Feedback> feedbackLists = new ArrayList<>();

        try {
            con = DBConnection.getConnection();
            String sql = "SELECT * FROM feedback";
            stmt = con.createStatement();
             rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int fd_id           = rs.getInt(1);
                String username     = rs.getString(2);
                String role         = rs.getString(3);
                String feedbackText = rs.getString(4);
                String feedbackDate = rs.getString(5);
                String rate         = rs.getString(6);
                String replyMessage = rs.getString(7);

                feedbackLists.add(new Feedback(fd_id, username, role, feedbackText, feedbackDate, rate, replyMessage));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbackLists;
    }

    // Method to update feedback (including reply message)
 
    public static boolean updateFeedback(String fd_id, String username, String role, String feedbackText, String feedbackDate, String rate, String replyMessage) {
      

        try {
            con = DBConnection.getConnection();
            stmt = con.createStatement();

            String sql = "UPDATE feedback SET username='" + username + "', role='" + role + "',feedbackText='" + feedbackText + "',feedbackDate='" + feedbackDate + "', rate='" + rate + "',replyMessage='" + replyMessage + "' " 
                    + "WHERE fd_id='" + fd_id + "'";


            int rs = stmt.executeUpdate(sql);

            if (rs > 0) {
                isSuccess = true;
            } else {
                isSuccess = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }


    // Method to delete feedback
    public static boolean deleteFeedback(String id) {
     
    	
		int convID = Integer.parseInt(id);
		try {
			
			con=DBConnection.getConnection();
			stmt = con.createStatement();
			
			String sql = "Delete from feedback  Where fd_id='"+convID+"'";
			int rs = stmt.executeUpdate(sql);
			if(rs>0)
			{
				isSuccess = true;
			}
			
			else {
				isSuccess = false;	
			}
		}catch(Exception e)
		
		{
			e.printStackTrace();
		}
		
		return isSuccess;
		
		
}
}

