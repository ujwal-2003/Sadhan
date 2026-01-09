/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import database.MySqlConnection;
/**
 *
 * @author hp
 */
public class loginDao {

    public String validateUserAndGetRole(String username, String password) {
        // 1. Check Admin Table
        if (checkTable("admin", username, password)) return "Admin";
        
        // 2. Check Company Table
        if (checkTable("companies", username, password)) return "Company";
        
        // 3. Check User Table (Matched to "customers" based on your code)
        if (checkTable("customers", username, password)) return "User";

        return null; 
    }

    private boolean checkTable(String tableName, String username, String password) {
        // Using TRIM() and LOWER() helps prevent "Incorrect" errors caused by extra spaces or casing
        String sql = "SELECT * FROM " + tableName + " WHERE LOWER(TRIM(username)) = LOWER(?) AND TRIM(password) = ?";
        
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());
            
            try (ResultSet rs = pstmt.executeQuery()) {
                boolean isFound = rs.next();
                // Check your NetBeans Output window to see which table is being hit
                System.out.println("Login check in [" + tableName + "] for user [" + username + "]: " + isFound);
                return isFound; 
            }
        } catch (SQLException e) {
            System.out.println("SQL Error in " + tableName + ": " + e.getMessage()); 
            return false;
        }
    }

   public int getCompanyId(String username, String password) {
        // Ensure your 'companies' table also uses 'id' or 'company_id' correctly
        String sql = "SELECT id FROM companies WHERE LOWER(TRIM(username)) = LOWER(?) AND TRIM(password) = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id"); 
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Company ID: " + e.getMessage());
        }
        return -1;
    }

    public int getSeekerId(String username, String password) {
        // FIXED: Changed 'customer_id' to 'id' to match your CREATE TABLE script
        String sql = "SELECT id FROM customers WHERE LOWER(TRIM(username)) = LOWER(?) AND TRIM(password) = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("id"); 
            }
        } catch (SQLException e) {
            System.out.println("Error fetching Seeker ID: " + e.getMessage());
        }
        return -1;
    }
    public int getadminId(String username, String password) {
    // SQL to check admin table
    String sql = "SELECT id FROM admin WHERE username = ? AND password = ?";
    
    try (Connection conn = database.MySqlConnection.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id"); // Found admin, return their ID
            }
        }
    } catch (SQLException e) {
        System.out.println("Error authenticating admin: " + e.getMessage());
    }
    
    return -1; // Admin not found or error occurred
}
}




