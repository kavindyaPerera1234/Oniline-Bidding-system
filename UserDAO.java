package UserPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/auction_db", "root", "");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role IN ('buyer', 'seller', 'admin')";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String role = rs.getString("role");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String username = rs.getString("username");
                String password = rs.getString("password");

                UserModel user = null;
                switch (role.toLowerCase()) {
                    case "buyer":
                        String buyerAddress = rs.getString("address");
                        String nic = rs.getString("nic");
                        user = new UserModel(id, role, name, email, nic, buyerAddress, phone, username, password);
                        break;
                    case "seller":
                        String sellerAddress = rs.getString("address");
                        user = new UserModel(id, role, name, email, null, sellerAddress, phone, username, password);
                        break;
                    case "admin":
                        user = new UserModel(id, role, name, email, null, null, phone, username, password);
                        break;
                }
                
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public UserModel getUserByUsername(String username, String role) {
        String query = "SELECT * FROM users WHERE username = ? AND role = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, role);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String password = rs.getString("password");

                    switch (role.toLowerCase()) {
                        case "buyer":
                            String buyerAddress = rs.getString("address");
                            String nic = rs.getString("nic");
                            return new UserModel(id, role, name, email, nic, buyerAddress, phone, username, password);
                        case "seller":
                            String sellerAddress = rs.getString("address");
                            return new UserModel(id, role, name, email, null, sellerAddress, phone, username, password);
                        case "admin":
                            return new UserModel(id, role, name, email, null, null, phone, username, password);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
} 