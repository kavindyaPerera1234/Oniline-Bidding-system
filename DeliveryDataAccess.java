package delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDataAccess {
    
    // Insert a new delivery
    public boolean insertDelivery(Delivery delivery) {
        String sql = "INSERT INTO deliveries (bidId, winnerName, winnerAddress, deliveryCompanyName) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, delivery.getBidId());
            pstmt.setString(2, delivery.getWinnerName());
            pstmt.setString(3, delivery.getWinnerAddress());
            pstmt.setString(4, delivery.getDeliveryCompanyName());
            
            int affected = pstmt.executeUpdate();
            return affected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update an existing delivery
    public boolean updateDelivery(Delivery delivery) {
        String sql = "UPDATE deliveries SET bidId = ?, winnerName = ?, winnerAddress = ?, deliveryCompanyName = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, delivery.getBidId());
            pstmt.setString(2, delivery.getWinnerName());
            pstmt.setString(3, delivery.getWinnerAddress());
            pstmt.setString(4, delivery.getDeliveryCompanyName());
            pstmt.setInt(5, delivery.getId());
            
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete a delivery
    public boolean deleteDelivery(int id) {
        String sql = "DELETE FROM deliveries WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get a delivery by ID
    public Delivery getDeliveryById(int id) {
        String sql = "SELECT * FROM deliveries WHERE id = ?";
        Delivery delivery = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                delivery = new Delivery();
                delivery.setId(rs.getInt("id"));
                delivery.setBidId(rs.getInt("bidId"));
                delivery.setWinnerName(rs.getString("winnerName"));
                delivery.setWinnerAddress(rs.getString("winnerAddress"));
                delivery.setDeliveryCompanyName(rs.getString("deliveryCompanyName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return delivery;
    }
    
    // Get all deliveries
    public List<Delivery> getAllDeliveries() {
        String sql = "SELECT * FROM deliveries";
        List<Delivery> deliveries = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Delivery delivery = new Delivery();
                delivery.setId(rs.getInt("id"));
                delivery.setBidId(rs.getInt("bidId"));
                delivery.setWinnerName(rs.getString("winnerName"));
                delivery.setWinnerAddress(rs.getString("winnerAddress"));
                delivery.setDeliveryCompanyName(rs.getString("deliveryCompanyName"));
                deliveries.add(delivery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return deliveries;
    }
    
    // Get delivery companies for dropdown
    public List<String> getAllDeliveryCompanyNames() {
        String sql = "SELECT DISTINCT name FROM deliverycompanies ORDER BY name";
        List<String> companyNames = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                companyNames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return companyNames;
    }
    
    // Count all deliveries
    public int countDeliveries() {
        String sql = "SELECT COUNT(*) as total FROM deliveries";
        int count = 0;
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return count;
    }
} 