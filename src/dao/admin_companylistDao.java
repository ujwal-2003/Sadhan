/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.admin_companylistModel;
/**
 *
 * @author hp
 */
public class admin_companylistDao {

    /**
     * Fetch companies by search query
     * @param query the search string (username or email)
     * @return List of companies matching the query
     */
    public List<admin_companylistModel> fetchCompanies(String query) {
        List<admin_companylistModel> companies = new ArrayList<>();

        String sql = "SELECT username, email FROM companies WHERE username LIKE ? OR email LIKE ?";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + query + "%");
            pstmt.setString(2, "%" + query + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    companies.add(new admin_companylistModel(
                        rs.getString("username"),
                        rs.getString("email")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return companies;
    }
}
