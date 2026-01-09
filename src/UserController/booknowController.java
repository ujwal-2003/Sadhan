/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.booknowDAO;
import dao.bookingformDao;
import model.booknowModel;
import model.bookingformModel;
import view.bookingform;
import view.User_vehicleicon;
import java.util.Date;
import javax.swing.*;

/**
 * Controller to handle the flow from vehicle selection to final booking.
 */
public class booknowController {
    private booknowDAO dao = new booknowDAO();
    private bookingformDao insertDao = new bookingformDao();
    private bookingformModel bookingModel = new bookingformModel();

    /**
     * Called when user selects a vehicle icon. Validates availability.
     */
    public void processIconClick(User_vehicleicon viewIcon) {
        String[] statuses = dao.checkVehicleStatus(viewIcon.getVehicleDbId());

        if (statuses != null) {
            String adminStatus = statuses[0];
            if (!"approved".equalsIgnoreCase(adminStatus)) {
                JOptionPane.showMessageDialog(viewIcon, "Listing Status: " + adminStatus);
                return;
            }
            if (viewIcon.getOnButtonClick() != null) {
                viewIcon.getOnButtonClick().accept(viewIcon);
            }
        } else {
            JOptionPane.showMessageDialog(viewIcon, "Error: Could not verify vehicle status.");
        }
    }

    /**
     * Launches the Booking Form with enriched data and passes 'this' controller.
     */
    public void handleBookingRequest(int uId, int vId, int cId, JPanel view) {
        booknowModel data = dao.fetchBookingData(uId, vId, cId);

        if (data != null) {
            this.bookingModel = new bookingformModel(); // Reset for new request
            
               bookingform bf = new bookingform(
                        data.getUserId(),      // 1
                        data.getVehicleId(),   // 2
                        data.getCompanyId(),   // 3
                        data.getPricePerDay(), // 4
                        data.getUserName(),    // 5
                        data.getUserPhone(),   // 6
                        data.getUserEmail(),   // 7
                        data.getVehicleFullName(), // 8
                        data.getVehicleModel(),    // 9
                        this                   // 10 (Passing 'this' controller)
                        );

            bf.setVisible(true);
            bf.setLocationRelativeTo(null);

            java.awt.Window parent = SwingUtilities.getWindowAncestor(view);
            if (parent != null) parent.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Error: Vehicle data could not be retrieved.");
        }
    }

    /**
     * View calls this when a license image is uploaded.
     */
    public void updateLicenseData(String path, byte[] bytes) {
        if (bookingModel != null) {
            bookingModel.setLicensePath(path);
            bookingModel.setLicenseBytes(bytes);
        }
    }

    /**
     * Final database insertion logic.
     */
    public int confirmBooking(int uId, int vId, int cId, String address, 
                              Date start, Date end, int days, double total, 
                              boolean isSelfDrive) {
        
        if (address == null || address.trim().isEmpty()) return -2;
        if (start == null || end == null) return -3;
        
        bookingModel.setCustomerId(uId);
        bookingModel.setVehicleId(vId);
        bookingModel.setCompanyId(cId);
        bookingModel.setCurrentAddress(address);
        bookingModel.setStartDate(new java.sql.Date(start.getTime()));
        bookingModel.setEndDate(new java.sql.Date(end.getTime()));
        bookingModel.setTotalDays(days);
        bookingModel.setTotalPrice(total);
        bookingModel.setStatus("Pending");

        if (isSelfDrive && (bookingModel.getLicenseBytes() == null)) {
            return -4; 
        }

        return insertDao.insertBooking(bookingModel); 
    
    }
}