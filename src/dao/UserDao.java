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
        MySqlConnection mysql = new MySqlConnection();
        
            public boolean checkVehicle(UserData user) {
        String sql = "SELECT * FROM vehicleDetails WHERE numberPlate = ?";
        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getNumberPlate());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

  public void requestVehicleApproval(UserData user) {
             String sql = """
            INSERT INTO vehicle_details_request
            (model, brand, type, colour, numberPlate, price, image_front, image_side, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'PENDING')
        """;

        Connection conn = mysql.openConnection();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getModel());
            ps.setString(2, user.getBrand());
            ps.setString(3, user.getVehicletype());
            ps.setString(4, user.getColour());
            ps.setString(5, user.getNumberPlate());
            ps.setString(6, user.getPrice());
            ps.setBytes(7, user.getFrontImage());
            ps.setBytes(8, user.getSideImage());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            mysql.closeConnection(conn);
        }
    }

  
 
}



 
    

