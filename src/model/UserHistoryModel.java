/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class UserHistoryModel {
    private int customerId;
    private int vehicleId;
    private int companyId;
    private String model;
    private String price;
    private String startDate;
    private String endDate;
    private int totalDays;
    private String totalPrice;
    private String status;

    public UserHistoryModel(int customerId, int vehicleId, int companyId, String model, 
                            String price, String startDate, String endDate, 
                            int totalDays, String totalPrice, String status) {
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.companyId = companyId;
        this.model = model;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public int getVehicleId() { return vehicleId; }
    public int getCompanyId() { return companyId; }
    public String getModel() { return model; }
    public String getPrice() { return price; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public int getTotalDays() { return totalDays; }
    public String getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
    
}
