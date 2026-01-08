/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.bookingformModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class bookingformDao {

    private static final Logger logger = Logger.getLogger(bookingformDao.class.getName());

    /**
     * Inserts a booking into the database.
     * @param model Booking model containing all booking data.
     * @return The generated booking ID if successful, -1 otherwise.
     */
    public int insertBooking(bookingformModel model) {
        String sql = "INSERT INTO bookings "
                   + "(customer_id, vehicle_id, company_id, current_address, start_date, end_date, "
                   + "total_days, total_price, status, license_path, license_image) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters
            pstmt.setInt(1, model.getCustomerId());
            pstmt.setInt(2, model.getVehicleId());
            pstmt.setInt(3, model.getCompanyId());
            pstmt.setString(4, model.getCurrentAddress());
            pstmt.setDate(5, model.getStartDate());
            pstmt.setDate(6, model.getEndDate());
            pstmt.setInt(7, model.getTotalDays());
            pstmt.setDouble(8, model.getTotalPrice());
            pstmt.setString(9, model.getStatus());
            pstmt.setString(10, model.getLicensePath());

            // Handle optional BLOB for license
            if (model.getLicenseBytes() != null && model.getLicenseBytes().length > 0) {
                pstmt.setBytes(11, model.getLicenseBytes());
            } else {
                pstmt.setNull(11, Types.LONGVARBINARY);
            }

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return new Booking ID
                    }
                }
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database Error: Failed to insert booking record", e);
        }

        return -1; // Return -1 on failure
    }

    /**
     * Optional: Update booking status
     */
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE id = ?";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, bookingId);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database Error: Failed to update booking status", e);
            return false;
        }
    }
}