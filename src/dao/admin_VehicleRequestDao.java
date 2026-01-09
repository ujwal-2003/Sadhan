/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.admin_VehicleRequestModel;

/**
 *
 * @author hp
 */
public class admin_VehicleRequestDao {
    
    public List<admin_VehicleRequestModel> getPendingRequests(String query) throws SQLException {
        List<admin_VehicleRequestModel> list = new ArrayList<>();
        String sql = "SELECT brand, model, numberPlate, price, company_id FROM vehicleDetails " +
                     "WHERE status = 'pending' AND (brand LIKE ? OR model LIKE ? OR numberPlate LIKE ? OR company_id LIKE ?)";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + query + "%";
            for (int i = 1; i <= 4; i++) pstmt.setString(i, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new admin_VehicleRequestModel(
                        rs.getString("brand") + " " + rs.getString("model"),
                        rs.getString("numberPlate"),
                        rs.getString("price"),
                        rs.getString("company_id")
                    ));
                }
            }
        }
        return list;
    }
    public ResultSet getVehicleFullDetails(String plateNumber) throws SQLException {
        String sql = "SELECT * FROM vehicleDetails WHERE numberPlate = ?";
        Connection conn = MySqlConnection.getInstance().getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, plateNumber);
        return pstmt.executeQuery();
    }
    public boolean updateVehicleStatus(String plate, String status, String reason) throws SQLException {
    String sql = "UPDATE vehicleDetails SET status = ?, rejection_reason = ? WHERE numberPlate = ?";
    try (Connection conn = MySqlConnection.getInstance().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, status);
        pstmt.setString(2, reason); // can be null for 'approved'
        pstmt.setString(3, plate);
        
        return pstmt.executeUpdate() > 0;
    }
}
}
