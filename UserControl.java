package UserPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UserPackage.DBConnection;


public class UserControl {
	

		private static boolean isSuccess;
		private static Connection con = null;
		private static PreparedStatement pstmt = null;
		private static ResultSet rs = null;
		
		public static boolean insertdata(String role,String name,String gmail,String nic,String address, String phone,String username, String password )
		{
			boolean isSuccess = false;
			
			try {
				//DBConnection
				con=DBConnection.getConnection();
				
				String sql = "";
				 if ("buyer".equals(role))
				 {
					sql= "INSERT INTO buyer (buyer_name, buyer_gmail, buyer_nic, buyer_address, buyer_phone, buyer_username, buyer_password) VALUES (?, ?, ?, ?, ?, ?, ?)";
				 }
				 else if("seller".equals(role)) {
					 
					 sql= "INSERT INTO seller (seller_name, seller_gmail, seller_nic, seller_address, seller_phone, seller_username, seller_password) VALUES (?, ?, ?, ?, ?, ?, ?)";
					 
				 }
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, gmail);
				pstmt.setString(3, nic);
				pstmt.setString(4, address);
				pstmt.setString(5, phone);
				pstmt.setString(6, username);
				pstmt.setString(7, password);
				
				int result = pstmt.executeUpdate();
				
				if(result>0)
				{
					isSuccess = true;
				}
				
				else {
					isSuccess = false;			}
			
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return isSuccess;
		}
		
		//login validation
		public static List<UserModel> loginValidate(String username, String password, String role) {
		    List<UserModel> users = new ArrayList<>();

		    try {
		        // DBConnection
		        con = DBConnection.getConnection();

		        String sql = "";

		        // Login query for different roles
		        if ("buyer".equals(role)) {
		            sql = "SELECT * FROM buyer WHERE buyer_username = ? AND buyer_password = ?";
		        } else if ("seller".equals(role)) {
		            sql = "SELECT * FROM seller WHERE seller_username = ? AND seller_password = ?";
		        } else if ("admin".equals(role)) {
		            sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
		        } else {
		            // Invalid role
		            return users;
		        }

		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, username);
		        pstmt.setString(2, password);

		        rs = pstmt.executeQuery();

		        // Process result based on the role
		        if (rs.next()) {
		            int id = rs.getInt(1);
		            String name = rs.getString(2);
		            String gmail = rs.getString(3);
		            String nic = rs.getString(4);
		            String address = rs.getString(5);
		            String phone = rs.getString(6);
		            String usernameDB = rs.getString(7);
		            String passwordDB = rs.getString(8);

		            // Create UserModel based on the role
		            UserModel u = null;
		            if ("buyer".equals(role)) {
		                u = new UserModel(id, role, name, gmail, nic, address, phone, usernameDB, passwordDB);
		            } else if ("seller".equals(role)) {
		                u = new UserModel(id, role, name, gmail, nic, address, phone, usernameDB, passwordDB);
		            } else if ("admin".equals(role)) {
		                u = new UserModel(id, role, name, gmail, null, null, phone, usernameDB, passwordDB); // Admin doesn't have address or NIC
		            }
		            users.add(u);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        closeResources();
		    }
		    return users;
		}

		public static boolean validateAdmin(String username, String password) {
		    boolean valid = false;
		    try {
		        con = DBConnection.getConnection();
		        String sql = "SELECT * FROM admin WHERE admin_username='" + username + "' AND admin_password='" + password + "'";
		        rs = con.createStatement().executeQuery(sql);
		        if (rs.next()) {
		            valid = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return valid;
		}

		public static boolean validateManager(String username, String password) {
		    boolean valid = false;
		    try {
		        con = DBConnection.getConnection();
		        String sql = "SELECT * FROM manager WHERE admin_username='" + username + "' AND admin_password='" + password + "'";
		        rs = con.createStatement().executeQuery(sql);
		        if (rs.next()) {
		            valid = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return valid;
		}

		//Display User
		public static List<UserModel>userProfile(String role,String Id){
			
			int convertID = Integer.parseInt(Id);
			
			List<UserModel> user = new ArrayList<>();
			
			try {
				con=DBConnection.getConnection();
				
				String sql = "";

		        if ("buyer".equals(role)) {
		            sql = "SELECT * FROM buyer WHERE buyer_id = ?";
		        } else if ("seller".equals(role)) {
		            sql = "SELECT * FROM seller WHERE seller_id = ?";
		        }

		        pstmt = con.prepareStatement(sql);
		        pstmt.setInt(1, convertID);
		        
		        rs = pstmt.executeQuery();
	            
	            if(rs.next())
	            {

	        		int id         = rs.getInt(1);
	        		String name    =rs.getString(2);
	        		String gmail   =rs.getString(3);
	        	    String nic     =rs.getString(4);
	        		String address =rs.getString(5);
	        		String phone   =rs.getString(6);
	        		String username=rs.getString(7);
	        		String password=rs.getString(8);
	        		
	        		UserModel u = new UserModel(id,role,name,gmail,nic,address,phone,username,password);
	        		user.add(u);
	            }
				
			}catch(SQLException e)
			{
				e.printStackTrace();
				
			}
			return user;
		}
		
		
		//Update Data
		public static boolean updateProfile(String id,String role,String name, String gmail,String nic ,String address, String phone,String username,String password) {
			
			try {
				con = DBConnection.getConnection();
				
				String sql="";
				if ("buyer".equals(role)) {
		            sql = "UPDATE buyer SET buyer_name = ?, buyer_gmail = ?, buyer_nic = ?, buyer_address = ?, buyer_phone = ?, buyer_username = ?, buyer_password = ? WHERE buyer_id = ?";
		        } else if ("seller".equals(role)) {
		            sql = "UPDATE seller SET seller_name = ?, seller_gmail = ?, seller_nic = ?, seller_address = ?, seller_phone = ?, seller_username = ?, seller_password = ? WHERE seller_id = ?";
		        }
		        	
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, gmail);
				pstmt.setString(3, nic);
				pstmt.setString(4, address);
				pstmt.setString(5, phone);
				pstmt.setString(6, username);
				pstmt.setString(7, password);
				pstmt.setInt(8, Integer.parseInt(id));
				
				int result = pstmt.executeUpdate();
				if(result>0)
				{
					isSuccess = true;
				}
				
				else {
					isSuccess = false;			}
			
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			return isSuccess;
		}
		
		//Delete profile data
		
		public static boolean deleteAccount(String Id,String role) {
			
			int convID = Integer.parseInt(Id);
			try {
				
				con=DBConnection.getConnection();
				
				 String sql = "";
			        if ("buyer".equalsIgnoreCase(role)) {
			            sql = "DELETE FROM buyer WHERE buyer_id = ?";
			        } else if ("seller".equalsIgnoreCase(role)) {
			            sql = "DELETE FROM seller WHERE seller_id = ?";
			        }
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, convID);
				
				int result = pstmt.executeUpdate();
				if(result>0)
				{
					isSuccess = true;
				}
				
				else {
					isSuccess = false;	
				}
			}catch(SQLException e)
			
			{
				e.printStackTrace();
			}
			
			return isSuccess;
			
			
	}

	private static void closeResources() {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}



