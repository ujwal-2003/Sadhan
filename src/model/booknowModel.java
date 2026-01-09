/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author hp
 */
public class booknowModel {
    // Data fields
    private int userId, vehicleId, companyId;
    private String userName, userPhone, userEmail, vehicleFullName, vehicleModel;
    private double pricePerDay;
    
    // UI fields (Added availability to support the Icon/Card view logic)
    private String availabilityStatus;
    private ImageIcon vehicleIcon;

    // 1. Full Constructor (Used by DAO to pack all fetched data)
    public booknowModel(int userId, int vehicleId, int companyId, String userName, 
                        String userPhone, String userEmail, String vehicleFullName, 
                        String vehicleModel, double pricePerDay) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.companyId = companyId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.vehicleFullName = vehicleFullName;
        this.vehicleModel = vehicleModel;
        this.pricePerDay = pricePerDay;
    }

    // 2. Minimal Constructor (Useful for the Icon/Card view specifically)
    public booknowModel(int vehicleId, String vehicleFullName, double pricePerDay, String status, ImageIcon icon) {
        this.vehicleId = vehicleId;
        this.vehicleFullName = vehicleFullName;
        this.pricePerDay = pricePerDay;
        this.availabilityStatus = status;
        this.vehicleIcon = icon;
    }

    // 3. No-Args Constructor
    public booknowModel() {}

    // --- GETTERS ---
    public int getUserId() { return userId; }
    public int getVehicleId() { return vehicleId; }
    public int getCompanyId() { return companyId; }
    public String getUserName() { return userName; }
    public String getUserPhone() { return userPhone; }
    public String getUserEmail() { return userEmail; }
    public String getVehicleFullName() { return vehicleFullName; }
    public String getVehicleModel() { return vehicleModel; }
    public double getPricePerDay() { return pricePerDay; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public ImageIcon getVehicleIcon() { return vehicleIcon; }

    // --- SETTERS ---
    public void setUserId(int userId) { this.userId = userId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public void setUserName(String userName) { this.userName = userName; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    public void setVehicleFullName(String vehicleFullName) { this.vehicleFullName = vehicleFullName; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setAvailabilityStatus(String status) { this.availabilityStatus = status; }
    public void setVehicleIcon(ImageIcon icon) { this.vehicleIcon = icon; }
}