/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class CompanyDashboardModel {
    private int vehicleId;
    private String model;
    private int customerId;
    private int bookingId;
    private String customerName;
    private String startDate;
    private String endDate;
    private double pricePerDay;
    private double totalPrice;
    private String status;

    // Constructor
    public CompanyDashboardModel(int vId, String model, int cId, int bId, String cName, 
                               String start, String end, double price, double total, String status) {
        this.vehicleId = vId;
        this.model = model;
        this.customerId = cId;
        this.bookingId = bId;
        this.customerName = cName;
        this.startDate = start;
        this.endDate = end;
        this.pricePerDay = price;
        this.totalPrice = total;
        this.status = status;
    }

    // Getters
    public int getVehicleId() { return vehicleId; }
    public String getModel() { return model; }
    public int getCustomerId() { return customerId; }
    public int getBookingId() { return bookingId; }
    public String getCustomerName() { return customerName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public double getPricePerDay() { return pricePerDay; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }

    
}
