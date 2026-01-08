/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.bookingformDao;
import java.util.Date;
import model.bookingformModel;
/**
 *
 * @author hp
 */
public class bookingformController {
    private bookingformDao dao = new bookingformDao();

     public int confirmBooking(bookingformModel model) {
        // 1. Business Logic / Validation
        if (model == null) return -1;
        
        if (model.getTotalDays() <= 0) {
            System.err.println("Validation Error: Rental duration must be at least 1 day.");
            return -1;
        }

        if (model.getTotalPrice() <= 0) {
            System.err.println("Validation Error: Price calculation failed.");
            return -1;
        }

        // 2. Data Transformation (If needed)
        // For example, ensuring the status is always "PENDING" on new bookings
        if (model.getStatus() == null || model.getStatus().isEmpty()) {
            model.setStatus("PENDING");
        }

        // 3. Delegate to DAO
        return dao.insertBooking(model);
    }
     public int processBooking(int custId, int vId, int compId, String address, 
                              Date start, Date end, double rate, 
                              boolean isSelfDrive, String licPath, byte[] licBytes) {
        
        // 1. Validation Logic
        if (address == null || address.trim().isEmpty() || address.equalsIgnoreCase("Enter Location")) {
            return -2; // Code for Missing Address
        }
        if (start == null || end == null) {
            return -3; // Code for Missing Dates
        }
        if (isSelfDrive && licBytes == null) {
            return -4; // Code for Missing License
        }

        // 2. Business Logic: Calculation
        long diff = Math.abs(end.getTime() - start.getTime());
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        if (days <= 0) days = 1;

        double total = days * rate;
        if (isSelfDrive) total *= 1.50;

        // 3. Model Preparation
        bookingformModel model = new bookingformModel(
            custId, vId, compId, address,
            new java.sql.Date(start.getTime()),
            new java.sql.Date(end.getTime()),
            days, total, "Pending", licPath, licBytes
        );

        // 4. Persistence via DAO
        return dao.insertBooking(model);
    }
}
