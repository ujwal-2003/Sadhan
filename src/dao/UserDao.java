/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.UserData;
import java.sql.*;
import database.MySqlConnection;
/**
 *
 * @author hp
 */
public class UserDao {
      MySqlConnection mysql = MySqlConnection.getInstance();
        
      public boolean checkVehicle(String numberPlate) {
        String sql = "SELECT 1 FROM vehicleDetails WHERE numberPlate = ?";
        Connection conn = mysql.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, numberPlate);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
             e.printStackTrace();
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

       public boolean requestVehicleApproval(UserData user) {
             String sql = """
            INSERT INTO vehicleDetails
            (company_id, model, brand, type, colour, numberPlate, price, image_front, image_side, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending')
        """;

        Connection conn = mysql.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user.getCompanyId());
            ps.setString(2, user.getModel());
            ps.setString(3, user.getBrand());
            ps.setString(4, user.getVehicletype());
            ps.setString(5, user.getColour());
            ps.setString(6, user.getNumberPlate());
            ps.setString(7, user.getPrice());
            ps.setBytes(8, user.getFrontImage());
            ps.setBytes(9, user.getSideImage());

            return ps.executeUpdate() > 0; // Return inside the block
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        mysql.closeConnection(conn);
    }
       }
}



 
    

