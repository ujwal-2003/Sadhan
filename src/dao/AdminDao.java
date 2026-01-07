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
public class AdminDao {
        MySqlConnection mysql = MySqlConnection.getInstance();

    // 1️⃣ Get all pending vehicles
    public ResultSet getPendingVehicles() {

        String sql = "SELECT * FROM vehicleDetails WHERE status = 'pending'";
        Connection conn = mysql.openConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
        // 2️⃣ Approve vehicle
    public boolean approveVehicle(int vehicleId) {

        String sql = "UPDATE vehicleDetails SET status = 'approved' WHERE id = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
        // 3️⃣ Reject vehicle
    public boolean rejectVehicle(int vehicleId, String reason) {

        String sql = """
            UPDATE vehicleDetails 
            SET status = 'rejected', rejection_reason = ?
            WHERE id = ?
        """;

        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, reason);
            ps.setInt(2, vehicleId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
    
}
