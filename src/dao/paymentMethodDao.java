/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.paymentMethodModel;
import database.MySqlConnection;
import java.sql.*;
/**
 *
 * @author hp
 */
public class paymentMethodDao {
         // 1. Check if Reference ID is truly unique
    
    
     // 1. Check if Reference ID is truly unique
    public boolean isReferenceUnique(String ref) {
        String sql = "SELECT COUNT(*) FROM payments WHERE transaction_ref = ?";
        try (Connection conn = new MySqlConnection().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, ref);
            ResultSet rs = pst.executeQuery();
            return rs.next() && rs.getInt(1) == 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
     public byte[] getCompanyQR(int compId) {
        String sql = "SELECT qr_code FROM companies WHERE id = ?";
        try (Connection conn = new MySqlConnection().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, compId);
            ResultSet rs = pst.executeQuery();
            return rs.next() ? rs.getBytes("qr_code") : null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
        public ResultSet getPendingBookings() {
        String query =
            "SELECT p.id AS payment_id, b.id AS booking_id, v.id AS vehicle_id, " +
            "c.name AS name, b.current_address AS address, c.phone AS phone, " +
            "b.start_date AS start_date, b.end_date AS end_date, b.total_days AS duration, " +
            "v.price AS price_per_day, p.amount AS total_amount, " +
            "p.payment_receipt AS receipt_image " +
            "FROM payments p " +
            "JOIN bookings b ON p.booking_id = b.id " +
            "JOIN vehicleDetails v ON b.vehicle_id = v.id " +
            "JOIN customers c ON b.customer_id = c.id " +
            "WHERE LOWER(p.status) = 'pending'"; // Simplified to catch the 'pending' status

        try {
            Connection con = new MySqlConnection().getConnection();
            PreparedStatement pst = con.prepareStatement(query, 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
         public boolean updateRequestStatus(int pId, int bId, int vId, String vehicleStatus) {
    Connection conn = null;
    try {
        conn = database.MySqlConnection.getInstance().getConnection();
        conn.setAutoCommit(false); // Start Transaction

        /**
         * Logic Mapping:
         * If vehicle becomes 'booked' -> Booking is 'Approved' & Payment is 'paid'
         * If vehicle becomes 'available' -> Booking is 'Rejected' & Payment is 'failed'
         */
        String bookingStatus = "booked".equals(vehicleStatus) ? "Approved" : "Rejected";
        String paymentStatus = "booked".equals(vehicleStatus) ? "paid" : "failed";

        // 1. Update Booking Table
        try (PreparedStatement pstB = conn.prepareStatement("UPDATE bookings SET status = ? WHERE id = ?")) {
            pstB.setString(1, bookingStatus);
            pstB.setInt(2, bId);
            pstB.executeUpdate();
        }

        // 2. Update Payment Table
        try (PreparedStatement pstP = conn.prepareStatement("UPDATE payments SET status = ? WHERE id = ?")) {
            pstP.setString(1, paymentStatus);
            pstP.setInt(2, pId);
            pstP.executeUpdate();
        }

        // 3. Update Vehicle Details Table
        try (PreparedStatement pstV = conn.prepareStatement("UPDATE vehicleDetails SET availability_status = ? WHERE id = ?")) {
            pstV.setString(1, vehicleStatus); 
            pstV.setInt(2, vId);
            pstV.executeUpdate();
        }

        conn.commit(); // Finalize all changes
        return true;
    } catch (SQLException e) {
        if (conn != null) {
            try { 
                conn.rollback(); // Undo everything if any one update fails
                System.err.println("Transaction rolled back due to: " + e.getMessage());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    } finally {
        if (conn != null) try { conn.close(); } catch (SQLException e) {}
    }
}

  private boolean doesCompanyExist(int compId) {
    // SELECT 1 is faster than SELECT * as it only checks for row existence
    String sql = "SELECT 1 FROM companies WHERE id = ?"; 
    
    try (Connection conn = new MySqlConnection().getConnection();
         PreparedStatement pst = conn.prepareStatement(sql)) {
        
        pst.setInt(1, compId);
        
        try (ResultSet rs = pst.executeQuery()) {
            return rs.next(); // Returns true if a record is found
        }
    } catch (SQLException e) {
        System.err.println("DAO Error (doesCompanyExist): " + e.getMessage());
        return false;
    }
}
     public boolean savePayment(paymentMethodModel payment) {
        String paymentSql = "INSERT INTO payments " +
            "(booking_id, customer_id, company_id, amount, payment_method, transaction_ref, payment_receipt, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String updateBooking = "UPDATE bookings SET status = ? WHERE id = ?";

        String updateVehicle = "UPDATE vehicleDetails SET availability_status = 'booked' " +
            "WHERE id = (SELECT vehicle_id FROM bookings WHERE id = ?)";

        try (Connection conn = new MySqlConnection().getConnection()) {
            conn.setAutoCommit(false); // Start Transaction

            try {
                // 1. Insert Payment (Setting all 8 parameters)
                try (PreparedStatement pst = conn.prepareStatement(paymentSql)) {
                    pst.setInt(1, payment.getBookingId());
                    pst.setInt(2, payment.getCustomerId());
                    pst.setInt(3, payment.getCompanyId());
                    pst.setDouble(4, payment.getAmount());
                    pst.setString(5, payment.getPaymentMethod());
                    pst.setString(6, payment.getTransactionRef());
                    pst.setBinaryStream(7, payment.getPaymentReceipt()); // The receipt image
                    pst.setString(8, payment.getStatus()); // Force 'pending'
                    pst.executeUpdate();
                }

                // 2. Update Booking Status dynamically to 'pending'
                try (PreparedStatement pstB = conn.prepareStatement(updateBooking)) {
                    pstB.setString(1, payment.getStatus()); 
                    pstB.setInt(2, payment.getBookingId());
                    pstB.executeUpdate();
                }

                // 3. Update Vehicle to 'booked' to lock it
                try (PreparedStatement pstV = conn.prepareStatement(updateVehicle)) {
                    pstV.setInt(1, payment.getBookingId());
                    pstV.executeUpdate();
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("DAO Transaction Error: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


    
  
