/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.InputStream;



/**
 *
 * @author hp
 */
/**
 * Model class representing a unique Payment Transaction.
 * Ties together Booking, Customer, and Company data.
 */
public class paymentMethodModel {
    private int bookingId;
    private int customerId;
    private int companyId;
    private double amount;
    private String paymentMethod;
    private String transactionRef;
    private InputStream paymentReceipt; // Used for saving image to database
    private byte[] companyQr;           // Used for displaying QR to user
    private String status;

    // Default Constructor
    public paymentMethodModel() {}

     public paymentMethodModel(int bookingId, int customerId, int companyId,
                          double amount, String paymentMethod, String transactionRef,
                          InputStream paymentReceipt, String status) {
    this.bookingId = bookingId;
    this.customerId = customerId;
    this.companyId = companyId;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.transactionRef = transactionRef;
    this.paymentReceipt = paymentReceipt;
    this.status = status;
}


    // Getters
    public int getBookingId() { return bookingId; }
    public int getCustomerId() { return customerId; }
    public int getCompanyId() { return companyId; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getTransactionRef() { return transactionRef; }
    public InputStream getPaymentReceipt() { return paymentReceipt; }
    public String getStatus() { return status; }
    public byte[] getCompanyQr() { return companyQr; }

    // Setters (Adding missing ones for flexibility)
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setTransactionRef(String transactionRef) { this.transactionRef = transactionRef; }
    public void setPaymentReceipt(InputStream paymentReceipt) { this.paymentReceipt = paymentReceipt; }
    public void setStatus(String status) { this.status = status; }
    public void setCompanyQr(byte[] companyQr) { this.companyQr = companyQr; }

    /**
     * Debugging helper: Use this to print the model data to console 
     * to check if IDs are valid (prevents Foreign Key errors).
     */
    @Override
    public String toString() {
        return "PaymentModel{" +
                "bookingId=" + bookingId +
                ", customerId=" + customerId +
                ", companyId=" + companyId +
                ", amount=" + amount +
                ", ref='" + transactionRef + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}