/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import model.CompanyHistoryModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hp
 */
public class CompanyHistoryDao {
    
    public List<CompanyHistoryModel> fetchHistory(int loggedInCompanyId) throws SQLException {
        List<CompanyHistoryModel> historyList = new ArrayList<>();
        String sql = "SELECT b.company_id, b.vehicle_id, b.customer_id, c.full_name, c.contact_no, " +
                     "b.current_address, b.start_date, b.end_date, v.price, b.total_days, b.total_price " +
                     "FROM bookings b " +
                     "JOIN customers c ON b.customer_id = c.id " +
                     "JOIN vehicleDetails v ON b.vehicle_id = v.id " +
                     "WHERE b.company_id = ?";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, loggedInCompanyId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                historyList.add(new CompanyHistoryModel(
                    rs.getInt("company_id"), rs.getInt("vehicle_id"), rs.getInt("customer_id"),
                    rs.getString("full_name"), rs.getString("contact_no"), rs.getString("current_address"),
                    rs.getString("start_date"), rs.getString("end_date"), rs.getBigDecimal("price"),
                    rs.getInt("total_days"), rs.getBigDecimal("total_price")
                ));
            }
        }
        return historyList;
    }
    
}
