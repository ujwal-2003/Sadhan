/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DBConnection;
import model.VehicleBooking;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author WELCOME
 */
public class VehicleBookingDao {
    public boolean insert(VehicleBooking v) {

        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO vehicle_booking "
                    + "(full_name, phone_number, email, address, city, province, "
                    + "pickup_date, pickup_time, pickup_location, "
                    + "return_date, return_time, return_location, "
                    + "rental_duration, driver_option, driver_licence) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, v.fullName);
            ps.setString(2, v.phone);
            ps.setString(3, v.email);
            ps.setString(4, v.address);
            ps.setString(5, v.city);
            ps.setString(6, v.province);

            ps.setDate(7, v.pickupDate);
            ps.setString(8, v.pickupTime);
            ps.setString(9, v.pickupLocation);

            ps.setDate(10, v.returnDate);
            ps.setString(11, v.returnTime);
            ps.setString(12, v.returnLocation);

            ps.setString(13, v.rentalDuration);
            ps.setString(14, v.driverOption);
            ps.setString(15, v.driverLicence);

            status = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
}
