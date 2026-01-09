/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.companyRegisterModel;
import java.sql.*;
import util.RegistrationResult; 
import java.sql.*;
import database.MySqlConnection;

/**
 *
 * @author prachisilwal
 */
public class companyRegisterDao {

    // 1. Save Company (Registration)
    public RegistrationResult saveCompany(String name, String user, String contact, 
                                          String email, String address, String pass, 
                                          String security, byte[] qrData) {
        
        String sql = "INSERT INTO companies (company_name, username, contact_no, email, address, password, security_question_answer, qr_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, user);
            pstmt.setString(3, contact);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, pass);
            pstmt.setString(7, security);
            
            if (qrData != null) {
                pstmt.setBytes(8, qrData); 
            } else {
                pstmt.setNull(8, java.sql.Types.LONGVARBINARY);
            }
            
            int rows = pstmt.executeUpdate();
            return rows > 0 ? new RegistrationResult(true, "Success") : new RegistrationResult(false, "Failed");
            
        } catch (SQLException e) {
            return new RegistrationResult(false, "DB Error: " + e.getMessage());
        }
    }

    // 2. Login Company
    public int loginCompany(String username, String password) throws SQLException {
        String sql = "SELECT id FROM companies WHERE username = ? AND password = ?";
        
        // Using the Singleton connection here too for consistency
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id"); 
                }
            }
        } catch (Exception e) {
            throw new SQLException("Login Query Failed", e);
        }
        return -1; 
    }
}