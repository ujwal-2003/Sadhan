/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */   
public class admin_bookingsModel {
    private int companyId, vehicleId, userId, totalDays; // Added totalDays
    private String companyName, userName, location, vehicleName, startDate, endDate, pricePerDay, totalPrice;

    public admin_bookingsModel(int companyId, int vehicleId, int userId, String companyName, String userName, 
                               String location, String vehicleName, String startDate, String endDate, 
                               int totalDays, String pricePerDay, String totalPrice) { // Added int totalDays
        this.companyId = companyId;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.totalDays = totalDays; // Initialize
        this.companyName = companyName;
        this.userName = userName;
        this.location = location;
        this.vehicleName = vehicleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerDay = pricePerDay;
        this.totalPrice = totalPrice;
    }

    // New Getter
    public int getTotalDays() { return totalDays; }

    // Existing Getters
    public int getCompanyId() { return companyId; }
    public int getVehicleId() { return vehicleId; }
    public int getUserId() { return userId; }
    public String getCompanyName() { return companyName; }
    public String getUserName() { return userName; }
    public String getLocation() { return location; }
    public String getVehicleName() { return vehicleName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getPricePerDay() { return pricePerDay; }
    public String getTotalPrice() { return totalPrice; }
}
    

