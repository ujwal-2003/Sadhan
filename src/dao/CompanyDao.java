/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Company;
import java.sql.*;

/**
 *
 * @author prachisilwal
 */
public class CompanyDao {

    private String url = "jdbc:mysql://localhost:3306/yourdatabase";
    private String dbUser = "root";
    private String dbPass = "mydadaisgreat";

public boolean registerCompany(Company company) throws SQLException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String sql = "INSERT INTO companies (company_name, username, contact_no, email, address, password, security_question_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, company.getName());
            pst.setString(2, company.getUsername());
            pst.setString(3, company.getContact());
            pst.setString(4, company.getEmail());
            pst.setString(5, company.getAddress());
            pst.setString(6, company.getPassword());
            pst.setString(7, company.getSecurityAnswer());

            return pst.executeUpdate() > 0;
        }
    } catch (ClassNotFoundException e) {
        throw new SQLException("MySQL Driver not found", e);
        }
    }
}