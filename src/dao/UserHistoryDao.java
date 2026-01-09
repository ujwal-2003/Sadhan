/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.UserHistoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class UserHistoryDao {
    public List<UserHistoryModel> getUserHistory(int loggedInUserId) throws SQLException {
        List<UserHistoryModel> historyList = new ArrayList<>();
        String sql = "SELECT b.customer_id, b.vehicle_id, b.company_id, v.model, v.price, " +
                     "b.start_date, b.end_date, b.total_days, b.total_price, b.status " +
                     "FROM bookings b " +
                     "JOIN vehicleDetails v ON b.vehicle_id = v.id " +
                     "WHERE b.customer_id = ?";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, loggedInUserId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                historyList.add(new UserHistoryModel(
                    rs.getInt("customer_id"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("company_id"),
                    rs.getString("model"),
                    rs.getString("price"),
                    rs.getString("start_date"),
                    rs.getString("end_date"),
                    rs.getInt("total_days"),
                    rs.getString("total_price"),
                    rs.getString("status")
                ));
            }
        }
        return historyList;
    }
}
