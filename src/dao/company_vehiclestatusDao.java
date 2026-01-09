/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.company_vehiclestatusModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hp
 */
public class company_vehiclestatusDao {

    public List<company_vehiclestatusModel> getVehicleRequests(int companyId, String searchTerm) throws SQLException {
        List<company_vehiclestatusModel> list = new ArrayList<>();
        String sql = "SELECT * FROM vehicleDetails WHERE company_id = ? "
                   + "AND (model LIKE ? OR brand LIKE ? OR numberPlate LIKE ?)";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, companyId);
            String queryTerm = "%" + searchTerm + "%";
            pstmt.setString(2, queryTerm);
            pstmt.setString(3, queryTerm);
            pstmt.setString(4, queryTerm);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String fullName = rs.getString("brand") + " " + rs.getString("model");
                    list.add(new company_vehiclestatusModel(
                        fullName,
                        rs.getString("numberPlate"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }
    public ResultSet getVehicleByPlate(String plateNumber) throws SQLException {
        Connection conn = MySqlConnection.getInstance().getConnection();
        String sql = "SELECT * FROM vehicleDetails WHERE numberPlate = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, plateNumber);
        return pstmt.executeQuery();
    }
    public boolean deleteVehicle(String plateNumber) throws SQLException {
        String sql = "DELETE FROM vehicleDetails WHERE numberPlate = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plateNumber);
            return pstmt.executeUpdate() > 0;
        }
    }
}
