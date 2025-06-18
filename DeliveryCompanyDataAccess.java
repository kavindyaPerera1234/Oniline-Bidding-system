package delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryCompanyDataAccess {
    
    // Insert a new delivery company
    public boolean insertDeliveryCompany(DeliveryCompany company) {
        String sql = "INSERT INTO deliverycompanies (name, address, phoneNumber, logo, email) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getAddress());
            pstmt.setString(3, company.getPhoneNumber());
            pstmt.setString(4, company.getLogo());
            pstmt.setString(5, company.getEmail());
            
            int affected = pstmt.executeUpdate();
            return affected > 0;
            
        } catch (SQLException e) {
        	
            e.printStackTrace();
            
            return false;
        }
    }
    
    // Update an existing delivery company
    public boolean updateDeliveryCompany(DeliveryCompany company) {
        String sql = "UPDATE deliverycompanies SET name = ?, address = ?, phoneNumber = ?, logo = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, company.getName());
            pstmt.setString(2, company.getAddress());
            pstmt.setString(3, company.getPhoneNumber());
            pstmt.setString(4, company.getLogo());
            pstmt.setString(5, company.getEmail());
            pstmt.setInt(6, company.getId());
            
            int affected = pstmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Delete a delivery company
    public boolean deleteDeliveryCompany(int id) {
        String sql = "DELETE FROM deliverycompanies WHERE id = ?";
        
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
    
    // Get a delivery company by ID
    public DeliveryCompany getDeliveryCompanyById(int id) {
        String sql = "SELECT * FROM deliverycompanies WHERE id = ?";
        DeliveryCompany company = null;
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                company = new DeliveryCompany();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                company.setPhoneNumber(rs.getString("phoneNumber"));
                company.setLogo(rs.getString("logo"));
                company.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return company;
    }
    
    // Get all delivery companies
    public List<DeliveryCompany> getAllDeliveryCompanies() {
        String sql = "SELECT * FROM deliverycompanies";
        List<DeliveryCompany> companies = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                DeliveryCompany company = new DeliveryCompany();
                company.setId(rs.getInt("id"));
                company.setName(rs.getString("name"));
                company.setAddress(rs.getString("address"));
                company.setPhoneNumber(rs.getString("phoneNumber"));
                company.setLogo(rs.getString("logo"));
                company.setEmail(rs.getString("email"));
                companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return companies;
    }
    
    // Count total number of delivery companies
    public int countDeliveryCompanies() {
        String sql = "SELECT COUNT(*) FROM deliverycompanies";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return 0;
    }
}