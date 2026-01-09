/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.MySqlConnection; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.admin_bookingsModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author prachisilwal
 */
public class admin_bookingsDAO {
    
    public List<admin_bookingsModel> searchBookings(String query) {
        List<admin_bookingsModel> list = new ArrayList<>();

        // 1. Added b.total_days to the SELECT statement
        String sql = "SELECT b.company_id, b.vehicle_id, b.customer_id, " +
                     "c.company_name, cust.full_name AS user_name, b.current_address, " +
                     "v.model AS vehicle_name, b.start_date, b.end_date, " +
                     "b.total_days, v.price AS price_per_day, b.total_price " + // Include b.total_days
                     "FROM bookings b " +
                     "JOIN companies c ON b.company_id = c.id " +
                     "JOIN customers cust ON b.customer_id = cust.id " +
                     "JOIN vehicleDetails v ON b.vehicle_id = v.id " +
                     "WHERE c.company_name LIKE ? OR cust.full_name LIKE ? " +
                     "OR v.model LIKE ? OR b.current_address LIKE ? " +
                     "OR CAST(b.company_id AS CHAR) LIKE ? OR CAST(b.vehicle_id AS CHAR) LIKE ?";

        try (Connection conn = new MySqlConnection().getConnection(); 
             PreparedStatement pst = conn.prepareStatement(sql)) {

            String searchStr = "%" + query + "%";
            for (int i = 1; i <= 6; i++) {
                pst.setString(i, searchStr);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                // 2. Updated to include rs.getInt("total_days") in the model constructor
                list.add(new admin_bookingsModel(
                    rs.getInt("company_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("customer_id"),
                    rs.getString("company_name"),
                    rs.getString("user_name"),
                    rs.getString("current_address"),
                    rs.getString("vehicle_name"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getInt("total_days"),    // Match the new int parameter in your model
                    rs.getString("price_per_day"),
                    rs.getString("total_price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}
    

