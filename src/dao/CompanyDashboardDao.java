/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.CompanyDashboardModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class CompanyDashboardDao {
    public List<CompanyDashboardModel> getBookedVehicles(int companyId) throws SQLException {
        List<CompanyDashboardModel> list = new ArrayList<>();
        String sql = "SELECT b.id AS booking_id, b.vehicle_id, v.model, b.customer_id, c.full_name, " +
                     "b.start_date, b.end_date, v.price, b.total_price, b.status " +
                     "FROM bookings b " +
                     "INNER JOIN vehicleDetails v ON b.vehicle_id = v.id " +
                     "LEFT JOIN payments p ON b.id = p.booking_id " +
                     "LEFT JOIN customers c ON b.customer_id = c.id " +
                     "WHERE b.company_id = ? " +
                     "AND ((p.status = 'paid' AND b.status IN ('Approved', 'confirmed')) OR b.status = 'pending') " +
                     "ORDER BY b.id DESC";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, companyId);
            
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    // Mapping data into the updated CompanyDashboardModel
                    CompanyDashboardModel row = new CompanyDashboardModel(
                        rs.getInt("vehicle_id"),
                        rs.getString("model"),
                        rs.getInt("customer_id"),
                        rs.getInt("booking_id"),
                        rs.getString("full_name"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getDouble("price"),
                        rs.getDouble("total_price"),
                        rs.getString("status")
                    );
                    list.add(row);
                }
            }
        }
        return list;
    }

    /**
     * Executes an ACID-compliant transaction to return a vehicle.
     */
    public boolean completeBookingTransaction(int vId, int bId) throws SQLException {
        String updateVehicle = "UPDATE vehicleDetails SET availability_status = 'available' WHERE id = ?";
        String updateBooking = "UPDATE bookings SET status = 'Completed' WHERE id = ?";

        Connection conn = null;
        try {
            conn = MySqlConnection.getInstance().getConnection();
            // Start manual transaction
            conn.setAutoCommit(false); 
            
            try (PreparedStatement pstV = conn.prepareStatement(updateVehicle);
                 PreparedStatement pstB = conn.prepareStatement(updateBooking)) {
                
                pstV.setInt(1, vId);
                pstB.setInt(1, bId);

                int vRows = pstV.executeUpdate();
                int bRows = pstB.executeUpdate();

                // Commit only if both updates succeeded
                if (vRows > 0 && bRows > 0) {
                    conn.commit();
                    return true;
                } else {
                    conn.rollback();
                    return false;
                }
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                throw e;
            }
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Restore default state
                conn.close(); // Return to pool
            }
        }
    }
    
}
