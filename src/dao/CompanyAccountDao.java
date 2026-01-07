/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;

import model.CompanyAccount;

/**
 *
 * @author prachisilwal
 */
public class CompanyAccountDao {
    private final String URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private final String USER = "root";
    private final String PASS = "password";

    public CompanyAccount getByUsername(String username) throws SQLException {

        String sql = """
            SELECT company_name, contact_no, email, address
            FROM companies
            WHERE company_username = ?
        """;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CompanyAccount company = new CompanyAccount();
                company.setCompanyUsername(username);
                company.setCompanyName(rs.getString("company_name"));
                company.setContact(rs.getString("contact_no"));
                company.setEmail(rs.getString("email"));
                company.setAddress(rs.getString("address"));
                return company;
            }
        }
        return null;
    }

    public boolean update(CompanyAccount company) throws SQLException {

        String sql = """
            UPDATE companies
            SET company_name=?, contact_no=?, email=?, address=?
            WHERE company_username=?
        """;

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, company.getCompanyName());
            ps.setString(2, company.getContact());
            ps.setString(3, company.getEmail());
            ps.setString(4, company.getAddress());
            ps.setString(5, company.getCompanyUsername());

            return ps.executeUpdate() > 0;
        }
    }
    
}
