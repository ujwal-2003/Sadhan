/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.MySqlConnection;
import model.EarningReportModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class EarningReportDao {
    public List<EarningReportModel> getReportData(int companyId) throws SQLException {
        List<EarningReportModel> list = new ArrayList<>();
        String sql = "SELECT b.vehicle_id, b.id, b.start_date, b.end_date, " +
                     "DATEDIFF(b.end_date, b.start_date) AS days, v.price, b.total_price " +
                     "FROM bookings b " +
                     "JOIN vehicleDetails v ON b.vehicle_id = v.id " +
                     "WHERE b.company_id = ? AND (b.status = 'confirmed' OR b.status = 'completed')";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, companyId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int days = rs.getInt("days");
                list.add(new EarningReportModel(
                    rs.getInt("vehicle_id"), rs.getInt("id"), rs.getString("start_date"),
                    rs.getString("end_date"), days <= 0 ? 1 : days,
                    rs.getDouble("price"), rs.getDouble("total_price")
                ));
            }
        }
        return list;
    }

    public Map<String, Object> getSummaryData(int companyId) throws SQLException {
        Map<String, Object> summary = new HashMap<>();
        String sql = "SELECT COUNT(id) AS total_count, IFNULL(SUM(total_price), 0) AS grand_total " +
                     "FROM bookings WHERE company_id = ? AND (status = 'confirmed' OR status = 'completed')";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, companyId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                summary.put("count", rs.getInt("total_count"));
                summary.put("total", rs.getDouble("grand_total"));
            }
        }
        return summary;
    }
    
}
