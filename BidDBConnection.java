package onlineBiddingPackage;

import java.sql.Connection;
import java.sql.DriverManager;

public class BidDBConnection {
	private static String url ="jdbc:mysql://localhost:3306/auctions";
	private static String user ="root";
	private static String pass ="auction";
    private static Connection con; 
    
    public static Connection getConnection (){
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		con = DriverManager.getConnection(url,user,pass);
    	}
    	catch(Exception e) {
    		System.out.println("Database Not Connect !");
    	}
    	return con;
    }
}
