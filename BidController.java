package onlineBiddingPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BidController {

	
	//Connect DB
	private static boolean isSuccess;
	private static Connection con =null;
	private static Statement stmt =null;
	private static ResultSet rs= null;
	
	//Insert Data Function
	public static boolean insertdata(String buyer_name, String category, String bid_date, double amount, String email,String phone) {
		
		boolean isSuccess = false;
		
		try {
			//DB CONNECTION CALL
			con=BidDBConnection.getConnection();
			stmt=con.createStatement();
			
			//SQL QUERY
			String sql = "insert into auction values(0,'"+buyer_name+"','"+category+"','"+bid_date+"',"+amount+",'"+email+"','"+phone+"')";
			int rs = stmt.executeUpdate(sql);
			if(rs>0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
	
	//GetById
	public static List<BiddingModel> getById(String bid_Id){
		
		int convertedID = Integer.parseInt(bid_Id);
		
		ArrayList <BiddingModel> auction = new ArrayList<>();
		
		try {
			//DBConnection
			con=BidDBConnection.getConnection();
			stmt=con.createStatement();
			
			//Query
			String sql = "select * from auction where bid_id ='"+convertedID+"'";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int bid_id = rs.getInt(1);
				String buyer_name = rs.getString(2);
				String category = rs.getString(3);
				String bid_date = rs.getString(4);
				double amount = rs.getDouble(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				
				BiddingModel bm = new BiddingModel(bid_id,buyer_name,category,bid_date,amount,email,phone);
				auction.add(bm);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return auction;
	}
	
	//GetAll Data
	public static List< BiddingModel>getAllBid (){
		ArrayList <BiddingModel> auctions = new ArrayList<>();
		
		try {
			//DBConnection
			con=BidDBConnection.getConnection();
			stmt=con.createStatement();
			
			//Query
			String sql = "select * from auction ";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int bid_id = rs.getInt(1);
				String buyer_name = rs.getString(2);
				String category = rs.getString(3);
				String bid_date = rs.getString(4);
				double amount = rs.getDouble(5);
				String email = rs.getString(6);
				String phone = rs.getString(7);
				
				BiddingModel bm = new BiddingModel(bid_id,buyer_name,category,bid_date,amount,email,phone);
				auctions.add(bm);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return auctions;
	}
	
	//Update Data
	public static boolean updatedata(String bid_id,String buyer_name,String category,String bid_date,double amount,String email,String phone) {
		
		try {
			
			//DBConnection
			con=BidDBConnection.getConnection();
			stmt=con.createStatement();
			
			//SQL Query
			String sql= "update auction set buyer_name='"+buyer_name+"',category='"+category+"',bid_date='"+bid_date+"',amount="+amount+",email='"+email+"',phone='"+phone+"'"
					+ "where bid_id='"+bid_id+"'";
			
			int rs = stmt.executeUpdate(sql);
			
			if(rs>0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return isSuccess;
		
	}
	//Delete Data
	public static boolean deletedata(String bid_id) {
		int convID = Integer.parseInt(bid_id);
		
		try {
			//DBConnection
			con=BidDBConnection.getConnection();
			stmt=con.createStatement();
		    String sql = "delete from auction where bid_id='"+convID+"'";
		    
            int rs = stmt.executeUpdate(sql);
			
			if(rs>0) {
				isSuccess = true;
			}
			else {
				isSuccess = false;
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return isSuccess;
	}
	
	
	
}
