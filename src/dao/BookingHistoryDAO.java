package dao;

import database.DBConnection;
import model.VehicleBooking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingHistoryDAO {

    public List<VehicleBooking> getAllBookingHistory() {

        List<VehicleBooking> list = new ArrayList<>();

        String sql = """
            SELECT booking_id,
                   full_name,
                   pickup_date,
                   return_date,
                   pickup_location,
                   return_location,
                   driver_option
            FROM vehicle_booking
        """;

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                VehicleBooking v = new VehicleBooking();

                v.bookingId = rs.getInt("booking_id");
                v.fullName = rs.getString("full_name");
                v.pickupDate = rs.getDate("pickup_date");
                v.returnDate = rs.getDate("return_date");
                v.pickupLocation = rs.getString("pickup_location");
                v.returnLocation = rs.getString("return_location");
                v.driverOption = rs.getString("driver_option");

                list.add(v);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    
    public List<VehicleBooking> searchByCustomerName(String name) {

    List<VehicleBooking> list = new ArrayList<>();

    String sql = """
        SELECT booking_id, full_name, pickup_date, return_date,
               pickup_location, return_location, driver_option
        FROM vehicle_booking
        WHERE full_name LIKE ?
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setString(1, "%" + name + "%");
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            VehicleBooking v = new VehicleBooking();
            v.bookingId = rs.getInt("booking_id");
            v.fullName = rs.getString("full_name");
            v.pickupDate = rs.getDate("pickup_date");
            v.returnDate = rs.getDate("return_date");
            v.pickupLocation = rs.getString("pickup_location");
            v.returnLocation = rs.getString("return_location");
            v.driverOption = rs.getString("driver_option");

            list.add(v);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

}
