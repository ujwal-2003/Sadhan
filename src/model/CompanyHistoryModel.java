/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;

/**
 *
 * @author hp
 */
public class CompanyHistoryModel {
    
    private int companyId, vehicleId, customerId, totalDays;
    private String fullName, contactNo, address, startDate, endDate;
    private BigDecimal price, totalPrice;

    public CompanyHistoryModel(int companyId, int vehicleId, int customerId, String fullName, 
                               String contactNo, String address, String startDate, String endDate, 
                               BigDecimal price, int totalDays, BigDecimal totalPrice) {
        this.companyId = companyId;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.fullName = fullName;
        this.contactNo = contactNo;
        this.address = address;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getCompanyId() { return companyId; }
    public int getVehicleId() { return vehicleId; }
    public int getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getContactNo() { return contactNo; }
    public String getAddress() { return address; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public BigDecimal getPrice() { return price; }
    public int getTotalDays() { return totalDays; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    
}
