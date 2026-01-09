/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class company_bookingmanagementModel {
    // Encapsulated fields (Private for safety)
    private int payId, bookId, vehId, days;
    private String name, address, phone, start, end;
    private double rate, total;
    private byte[] receipt, license;

    // Standard Constructor
    public company_bookingmanagementModel(int pId, int bId, int vId, String name, String addr, String ph, 
                                  String start, String end, int days, double rate, double total, 
                                  byte[] receipt, byte[] license) {
        this.payId = pId; 
        this.bookId = bId; 
        this.vehId = vId;
        this.name = name; 
        this.address = addr; 
        this.phone = ph;
        this.start = start; 
        this.end = end; 
        this.days = days;
        this.rate = rate; 
        this.total = total;
        this.receipt = receipt; 
        this.license = license;
    }

    // --- Getters ---
    public int getPayId() { return payId; }
    public int getBookId() { return bookId; }
    public int getVehId() { return vehId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getStart() { return start; }
    public String getEnd() { return end; }
    public int getDays() { return days; }
    public double getRate() { return rate; }
    public double getTotal() { return total; }
    public byte[] getReceipt() { return receipt; }
    public byte[] getLicense() { return license; }

    // --- Setters (Useful for status updates or modifications) ---
    public void setReceipt(byte[] receipt) { this.receipt = receipt; }
    public void setLicense(byte[] license) { this.license = license; }
}