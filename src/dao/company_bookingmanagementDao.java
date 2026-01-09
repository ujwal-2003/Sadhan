/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.company_bookingmanagementModel;
import database.MySqlConnection;// Assuming this is your connection class

/**
 *
 * @author hp
 */
public class company_bookingmanagementDao {

    /**
     * Fetches all bookings where the payment is still 'pending' for a specific company.
     * This list populates the Company Admin's review dashboard.
     */
     public List<company_bookingmanagementModel> fetchPendingBookings(int companyId) {
    List<company_bookingmanagementModel> list = new ArrayList<>();
    
    // Updated Query: Select b.license_image (the BLOB column) instead of just the path
    String query = "SELECT p.id AS payment_id, b.id AS booking_id, b.vehicle_id, " +
                   "c.full_name AS customer_name, b.current_address, c.contact_no, " +
                   "b.start_date, b.end_date, b.total_days, " +
                   "p.amount AS total_amount, " +
                   "p.payment_receipt, b.license_image " + // Fetch the actual BLOB
                   "FROM payments p " +
                   "JOIN bookings b ON p.booking_id = b.id " +
                   "JOIN customers c ON b.customer_id = c.id " +
                   "WHERE p.status = 'pending' AND b.status = 'pending' AND p.company_id = ?";

    try (Connection con = new database.MySqlConnection().getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {
        
        pst.setInt(1, companyId); 

        try (ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                // 1. Fetch Receipt directly from DB (LONGBLOB)
                byte[] receiptBytes = rs.getBytes("payment_receipt");
                
                // 2. Fetch License directly from DB (LONGBLOB)
                // No more java.io.File or path checking needed!
                byte[] licenseBytes = rs.getBytes("license_image");

                // Calculate rates
                int days = rs.getInt("total_days");
                double total = rs.getDouble("total_amount");
                double rate = (days > 0) ? (total / days) : 0;

                // Add to list using the constructor
                list.add(new company_bookingmanagementModel(
                    rs.getInt("payment_id"), 
                    rs.getInt("booking_id"), 
                    rs.getInt("vehicle_id"),
                    rs.getString("customer_name"), 
                    rs.getString("current_address"), 
                    rs.getString("contact_no"),
                    rs.getString("start_date"), 
                    rs.getString("end_date"), 
                    days,
                    rate, 
                    total,
                    receiptBytes, // Byte array for jLabel3
                    licenseBytes  // Byte array for jLabel2
                ));
            }
        }
    } catch (SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
    }
    return list;
}

    /**
     * Processes Approval or Rejection in an Atomic Transaction.
     * All three tables (Payments, Bookings, VehicleDetails) must update together.
     */
     public boolean updateFullBookingStatus(int pId, int bId, int vId, String payStatus, String bookStatus, String vehStatus) {
    // SQL queries remain specific to each table's role
    String sqlPay = "UPDATE payments SET status = ? WHERE id = ?";
    String sqlBook = "UPDATE bookings SET status = ? WHERE id = ?";
    String sqlVeh = "UPDATE vehicleDetails SET availability_status = ? WHERE id = ?";

    Connection con = null;
    try {
        con = new database.MySqlConnection().getConnection();
        con.setAutoCommit(false); // Enable manual transaction control

        // 1. Update Payment Table (e.g., 'paid' or 'failed')
        try (PreparedStatement pstP = con.prepareStatement(sqlPay)) {
            pstP.setString(1, payStatus);
            pstP.setInt(2, pId);
            int payUpdated = pstP.executeUpdate();
            if (payUpdated == 0) throw new SQLException("Updating payment failed, no rows affected.");
        }

        // 2. Update Booking Table (e.g., 'Approved' or 'Rejected')
        try (PreparedStatement pstB = con.prepareStatement(sqlBook)) {
            pstB.setString(1, bookStatus);
            pstB.setInt(2, bId);
            int bookUpdated = pstB.executeUpdate();
            if (bookUpdated == 0) throw new SQLException("Updating booking failed, no rows affected.");
        }

        // 3. Update Vehicle Availability (e.g., 'booked' or 'available')
        try (PreparedStatement pstV = con.prepareStatement(sqlVeh)) {
            pstV.setString(1, vehStatus);
            pstV.setInt(2, vId);
            int vehUpdated = pstV.executeUpdate();
            if (vehUpdated == 0) throw new SQLException("Updating vehicle failed, no rows affected.");
        }

        // Finalize Transaction
        con.commit(); 
        System.out.println("Transaction Committed: Payment=" + payStatus + ", Booking=" + bookStatus + ", Vehicle=" + vehStatus);
        return true;

    } catch (SQLException e) {
        // Atomic Rollback: ensures if one part fails, no partial data is saved
        if (con != null) {
            try {
                con.rollback();
                System.err.println("CRITICAL: Transaction rolled back due to error: " + e.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    } finally {
        // Safe closure of the connection
        try { if (con != null) con.close(); } catch (SQLException ex) { ex.printStackTrace(); }
    }
     }
}
