package delivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    

	private static String URL="jdbc:mysql://localhost:3306/auctions";
	private static String USER="root";
	private static String PASSWORD="auction";
		
		
		
		private static Connection con;
		
		public static Connection getConnection() {
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(URL,USER,PASSWORD);
				}
			catch(Exception e)
			{
				System.out.println("Database Not Connect !");
				e.printStackTrace();
			}
			
			return con;
		}
}
