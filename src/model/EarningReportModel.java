/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class EarningReportModel {
    private int vehicleId;
    private int bookingId;
    private String startDate;
    private String endDate;
    private int noOfDays;
    private double pricePerDay;
    private double totalPrice;

    public EarningReportModel(int vehicleId, int bookingId, String startDate, String endDate, 
                              int noOfDays, double pricePerDay, double totalPrice) {
        this.vehicleId = vehicleId;
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.noOfDays = noOfDays;
        this.pricePerDay = pricePerDay;
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getVehicleId() { return vehicleId; }
    public int getBookingId() { return bookingId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public int getNoOfDays() { return noOfDays; }
    public double getPricePerDay() { return pricePerDay; }
    public double getTotalPrice() { return totalPrice; }
    
}
