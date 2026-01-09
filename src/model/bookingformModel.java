/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.InputStream;
import java.sql.Date;
/**
 *
 * @author hp
 */
public class bookingformModel {
    private int customerId, vehicleId, companyId, totalDays;
    private String currentAddress, status, licensePath; 
    private byte[] licenseBytes; 
    private java.sql.Date startDate, endDate;
    private double totalPrice;

    // 1. Default Constructor (Optional but recommended)
    public bookingformModel() {}

    // 2. Full Constructor with 11 parameters
    public bookingformModel(int customerId, int vehicleId, int companyId, String address, 
                            java.sql.Date start, java.sql.Date end, int days, double price, 
                            String status, String licensePath, byte[] license_image) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.companyId = companyId;
        this.currentAddress = address;
        this.startDate = start;
        this.endDate = end;
        this.totalDays = days;
        this.totalPrice = price;
        this.status = status;
        this.licensePath = licensePath;
        this.licenseBytes = license_image;
    }

    // --- GETTERS ---
    public int getCustomerId() { return customerId; }
    public int getVehicleId() { return vehicleId; }
    public int getCompanyId() { return companyId; }
    public String getCurrentAddress() { return currentAddress; }
    public java.sql.Date getStartDate() { return startDate; }
    public java.sql.Date getEndDate() { return endDate; }
    public int getTotalDays() { return totalDays; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    public String getLicensePath() { return licensePath; } 
    public byte[] getLicenseBytes() { return licenseBytes; }

    // --- SETTERS ---
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public void setCurrentAddress(String address) { this.currentAddress = address; }
    public void setStartDate(java.sql.Date start) { this.startDate = start; }
    public void setEndDate(java.sql.Date end) { this.endDate = end; }
    public void setTotalDays(int days) { this.totalDays = days; }
    public void setTotalPrice(double price) { this.totalPrice = price; }
    public void setStatus(String status) { this.status = status; }
    public void setLicensePath(String path) { this.licensePath = path; }
    public void setLicenseBytes(byte[] bytes) { this.licenseBytes = bytes; }
}