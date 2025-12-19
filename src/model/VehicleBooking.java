package model;

import java.sql.Date;

public class VehicleBooking {
    public int bookingId; // Added bookingId for history
    public String fullName;
    public String phone;
    public String email;
    public String address;
    public String city;
    public String province;

    public Date pickupDate;
    public String pickupTime;
    public String pickupLocation;

    public Date returnDate;
    public String returnTime;
    public String returnLocation;

    public String rentalDuration;
    public String driverOption;
    public String driverLicence;
}
