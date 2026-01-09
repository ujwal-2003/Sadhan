/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.*;
import model.booknowModel;

/**
 *
 * @author hp
 */
public class booknowDAO {
    public booknowModel fetchBookingData(int userId, int vehicleId, int companyId) {
        String vSql = "SELECT price, brand, model FROM vehicleDetails " +
                     "WHERE id = ? AND status = 'approved' AND availability_status = 'available'";
        String uSql = "SELECT full_name, contact_no, email FROM customers WHERE id = ?";

        try (Connection conn = MySqlConnection.getInstance().getConnection()) {
            double price = 0;
            String vFullName = "";
            String vModelOnly = "";
            String uName = "", uPhone = "", uEmail = "";

            // 1. Fetch Vehicle Data
            try (PreparedStatement pstV = conn.prepareStatement(vSql)) {
                pstV.setInt(1, vehicleId);
                ResultSet rsV = pstV.executeQuery();
                if (rsV.next()) {
                    price = rsV.getDouble("price");
                    vModelOnly = rsV.getString("model");
                    vFullName = rsV.getString("brand") + " " + vModelOnly;
                } else { 
                    return null; // Vehicle became unavailable or unapproved
                } 
            }

            // 2. Fetch User Data
            try (PreparedStatement pstU = conn.prepareStatement(uSql)) {
                pstU.setInt(1, userId);
                ResultSet rsU = pstU.executeQuery();
                if (rsU.next()) {
                    uName = rsU.getString("full_name");
                    uPhone = rsU.getString("contact_no");
                    uEmail = rsU.getString("email");
                }
            }

            // 3. Return the fully populated Model
            return new booknowModel(
                userId, vehicleId, companyId, 
                uName, uPhone, uEmail, 
                vFullName, vModelOnly, price
            );

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String[] checkVehicleStatus(int vehicleId) {
        // SQL to get both the admin approval status and the user availability status
    String sql = "SELECT status, availability_status FROM vehicleDetails WHERE id = ?";
    
    try (Connection conn = MySqlConnection.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, vehicleId);
        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                // Returns an array: index 0 is 'status', index 1 is 'availability_status'
                return new String[] { 
                    rs.getString("status"), 
                    rs.getString("availability_status") 
                };
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
    }

    
}
