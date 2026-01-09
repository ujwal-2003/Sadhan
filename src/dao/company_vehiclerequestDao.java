/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.company_vehiclerequestModel;
import java.sql.*;
import java.io.FileInputStream;
/**
 *
 * @author hp
 */
public class company_vehiclerequestDao {

    public boolean isPlateExists(Connection conn, String plate) throws SQLException {
        String sql = "SELECT COUNT(*) FROM vehicleDetails WHERE TRIM(numberPlate)=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, plate);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1) > 0;
            }
        }
    }

    public boolean insertVehicleRequest(company_vehiclerequestModel v) throws Exception {
        String sql = """
            INSERT INTO vehicleDetails
            (company_id, model, brand, type, colour, numberPlate, price,
             image_front, image_side, status)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'pending')
        """;

        try (Connection conn = database.MySqlConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             FileInputStream fis1 = new FileInputStream(v.getFrontImage());
             FileInputStream fis2 = new FileInputStream(v.getSideImage())) {

            ps.setInt(1, v.getCompanyId());
            ps.setString(2, v.getModel());
            ps.setString(3, v.getBrand());
            ps.setString(4, v.getType());
            ps.setString(5, v.getColour());
            ps.setString(6, v.getNumberPlate());
            ps.setDouble(7, v.getPrice());
            ps.setBinaryStream(8, fis1, (int) v.getFrontImage().length());
            ps.setBinaryStream(9, fis2, (int) v.getSideImage().length());

            return ps.executeUpdate() > 0;
        }
    }
}
