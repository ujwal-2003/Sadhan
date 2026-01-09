/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.MySqlConnection;
import model.company_vehiclestatusModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class forgetPasswordDao {
    // Replace with your actual database credentials
    private static final String URL = "jdbc:mysql://localhost:3306/sadhan";
    private static final String USER = "root";
    private static final String PASS = "actofgod12345";
    
    
public boolean resetPassword(String username, String email, String contact, String answer, String newPassword) {
    // These table names must match your database exactly
    String[] tables = {"admin", "companies", "customers"};
    
    for (String table : tables) {
        // Use TRIM() and LOWER() to ensure "ktm " matches "KTM"
        String query = "UPDATE " + table + " SET password = ? " +
                       "WHERE LOWER(TRIM(username)) = LOWER(?) " +
                       "AND LOWER(TRIM(email)) = LOWER(?) " +
                       "AND TRIM(contact_no) = ? " +
                       "AND LOWER(TRIM(security_question_answer)) = LOWER(?)";

        try (Connection conn = MySqlConnection.getInstance().getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, newPassword);
            pst.setString(2, username); // Controller already trimmed these
            pst.setString(3, email);
            pst.setString(4, contact);
            pst.setString(5, answer);

            int rowsAffected = pst.executeUpdate();

            // IMPORTANT: Check your NetBeans console for this message
            System.out.println("Checking " + table + " table... Rows updated: " + rowsAffected);

            if (rowsAffected > 0) {
                return true; 
            }

        } catch (SQLException ex) {
            // This prints if a column name (like 'contact_no') is missing in one table
            System.out.println("Error in table [" + table + "]: " + ex.getMessage());
        }
    }
    return false; 

}

}