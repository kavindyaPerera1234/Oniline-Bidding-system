package seller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemControl {
    
	private static boolean isSuccess;
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	
	
	//create
	public static boolean insertdata(String seller_name,String item_title,String category, String price, String start_time,String end_time,String description )
	{
		boolean isSuccess = false;
		
		try {
			con=DBConnection.getConnection();
			stmt=con.createStatement();
			
			String sql ="insert into items values (0,'"+seller_name+"','"+item_title+"','"+category+"','"+price+"','"+start_time+"','"+end_time+"','"+description+"')";
			int rs = stmt.executeUpdate(sql);
			
			if(rs>0)
			{
				isSuccess = true;
			}
			
			else {
				isSuccess = false;			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return isSuccess;
	}
	
	
	//display data control
	
	public static List<ItemModel> getById (String Id){
		
		int convertedID = Integer.parseInt(Id);
		
		ArrayList <ItemModel> item = new ArrayList<>();
		
		try {
			
			con = DBConnection.getConnection();
			stmt=con.createStatement();
			
			String sql = "Select * From items Where item_id '"+convertedID+"'";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int item_id        = rs.getInt(1);
			    String seller_name = rs.getString(2);
			    String item_title  = rs.getString(3);
			    String category    = rs.getString(4);
			    String price       = rs.getString(5);
			    String start_time  = rs.getString(6);
			    String end_time    = rs.getString(7);
			    String description = rs.getString(8);
			    
			    ItemModel ik = new ItemModel(item_id,seller_name,item_title,category,price,start_time,end_time,description);
			    item.add(ik);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return item;
	}
	
	//Display Data
	public static List<ItemModel> getAllItem(){
		
		ArrayList <ItemModel> items = new ArrayList<>();
		
try {
			
			con = DBConnection.getConnection();
			stmt=con.createStatement();
			
			String sql = "Select * From items" ;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int item_id        = rs.getInt(1);
			    String seller_name = rs.getString(2);
			    String item_title  = rs.getString(3);
			    String category    = rs.getString(4);
			    String price       = rs.getString(5);
			    String start_time  = rs.getString(6);
			    String end_time    = rs.getString(7);
			    String description = rs.getString(8);
			    
			    ItemModel ik = new ItemModel(item_id,seller_name,item_title,category,price,start_time,end_time,description);
			    items.add(ik);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return items;
		
	}
	
	
	//Update Data
	public static boolean updatedata(String item_id,String seller_name, String item_title,String category,String price, String start_time,String end_time,String description) {
		
		try {
			con = DBConnection.getConnection();
			stmt=con.createStatement();
			
			String sql = "Update items Set seller_name='"+seller_name+"',item_title='"+item_title+"',category='"+category+"',price='"+price+"',start_time='"+start_time+"',end_time='"+end_time+"',description='"+description+"'"
					     +"Where item_id='"+item_id+"'";
			
			int rs = stmt.executeUpdate(sql);
			if(rs>0)
			{
				isSuccess = true;
			}
			
			else {
				isSuccess = false;			}
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return isSuccess;
	}
		
		//Delete Data
		
		public static boolean deletedata(String Id) {
			
			int convID = Integer.parseInt(Id);
			try {
				
				con=DBConnection.getConnection();
				stmt = con.createStatement();
				
				String sql = "Delete from items  Where item_id='"+convID+"'";
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

