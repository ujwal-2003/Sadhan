/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Seeker;
import java.sql.*;

/**
 *
 * @author prachisilwal
 */
public class SeekerDao {
    
    private final String url = "jdbc:mysql://localhost:3306/yourdatabase";
    private final String dbUser = "root";
    private final String dbPass = "mydadaisgreat";

    public boolean registerSeeker(Seeker seeker) throws SQLException {
        String sql = "INSERT INTO customers (full_name, username, contact_no, email, address, password, security_question_answer) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPass);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, seeker.getFullName());
            pst.setString(2, seeker.getUsername());
            pst.setString(3, seeker.getContact());
            pst.setString(4, seeker.getEmail());
            pst.setString(5, seeker.getAddress());
            pst.setString(6, seeker.getPassword());
            pst.setString(7, seeker.getSecurityAnswer());

            int inserted = pst.executeUpdate();
            return inserted > 0;
        }
    }
}