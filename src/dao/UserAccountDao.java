/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import model.UserAccount;
/**
 *
 * @author prachisilwal
 */
public class UserAccountDao {
    private final String URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private final String USER = "root";
    private final String PASS = "mydadaisgreat";

    public UserAccount getByUsername(String username) throws SQLException {

        String sql = "SELECT full_name, contact_no, email, address FROM customers WHERE username=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                UserAccount user = new UserAccount();
                user.setUsername(username);
                user.setFullName(rs.getString("full_name"));
                user.setContact(rs.getString("contact_no"));
                user.setEmail(rs.getString("email"));
                user.setAddress(rs.getString("address"));
                return user;
            }
        }
        return null;
    }

    public boolean update(UserAccount user) throws SQLException {

        String sql = "UPDATE customers SET full_name=?, contact_no=?, email=?, address=? WHERE username=?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, user.getFullName());
            pst.setString(2, user.getContact());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getAddress());
            pst.setString(5, user.getUsername());

            return pst.executeUpdate() > 0;
        }
    }
}
