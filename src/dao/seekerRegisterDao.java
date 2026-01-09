/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.seekerRegisterModel;
import java.sql.*;

/**
 *
 * @author prachisilwal
 */
public class seekerRegisterDao {
    
    private final String url = "jdbc:mysql://localhost:3306/sadhan";
    private final String dbUser = "root";
    private final String dbPass = "actofgod12345";

    public boolean registerSeeker(seekerRegisterModel seeker) throws SQLException {
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