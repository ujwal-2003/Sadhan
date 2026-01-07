/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.UserDashboardModel;
import database.MySqlConnection;

/**
 *
 * @author hp
 */
public class UserDashboardDao {

    // Fetch User Name for Welcome Label
    public String getCustomerName(int userId) throws SQLException {
        String sql = "SELECT full_name FROM customers WHERE id = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() ? rs.getString("full_name") : null;
            }
        }
    }

    // Fetch List of Vehicles for the Grid
    public List<UserDashboardModel> getApprovedVehicles(String search) throws SQLException {
        List<UserDashboardModel> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
            "SELECT id, company_id, brand, model, price, image_side, availability_status " +
            "FROM vehicleDetails WHERE status = 'approved'"
        );

        boolean isSearching = search != null && !search.trim().isEmpty() && !search.equals("Search Vehicle");
        if (isSearching) {
            sql.append(" AND (LOWER(brand) LIKE ? OR LOWER(model) LIKE ?)");
        }

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            if (isSearching) {
                String pattern = "%" + search.trim().toLowerCase() + "%";
                pstmt.setString(1, pattern);
                pstmt.setString(2, pattern);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    UserDashboardModel m = new UserDashboardModel();
                    m.setId(rs.getInt("id"));
                    m.setCompanyId(rs.getInt("company_id"));
                    m.setBrand(rs.getString("brand"));
                    m.setModel(rs.getString("model"));
                    m.setPrice(rs.getDouble("price"));
                    m.setThumbnailBytes(rs.getBytes("image_side"));
                    m.setAvailabilityStatus(rs.getString("availability_status"));
                    list.add(m);
                }
            }
        }
        return list;
    }

    // Fetch Full Details for the "Book Now" popup
    public UserDashboardModel getVehicleFullDetails(int vehicleId) throws SQLException {
        String sql = "SELECT * FROM vehicleDetails WHERE id = ?";
        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, vehicleId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserDashboardModel m = new UserDashboardModel();
                    m.setId(rs.getInt("id"));
                    m.setCompanyId(rs.getInt("company_id"));
                    m.setBrand(rs.getString("brand"));
                    m.setModel(rs.getString("model"));
                    m.setType(rs.getString("type"));
                    m.setColour(rs.getString("colour"));
                    m.setNumberPlate(rs.getString("numberPlate"));
                    m.setPrice(rs.getDouble("price"));
                    m.setImageFrontBlob(rs.getBlob("image_front"));
                    m.setImageSideBlob(rs.getBlob("image_side"));
                    return m;
                }
            }
        }
        return null;
    }
}