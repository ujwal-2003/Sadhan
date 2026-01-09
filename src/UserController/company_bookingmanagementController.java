/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.company_bookingmanagementDao;
import model.company_bookingmanagementModel;
import view.company_bookingmanagementunit;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import view.company_bookingmanagementlist;

/**
 *
 * @author hp
 */
public class company_bookingmanagementController {

    private final company_bookingmanagementDao dao;

    public company_bookingmanagementController() {
        this.dao = new company_bookingmanagementDao();
    }

    /**
     * Populates the given container JPanel with booking cards.
     * @param container JPanel where booking cards will be displayed
     * @param userId ID of the company/user to fetch bookings for
     */
    public void populateBookings(JPanel container, int userId) {
        // Clear existing components to prevent duplication
        container.removeAll();

        try {
            // Fetch pending bookings for the given company/user
            List<company_bookingmanagementModel> bookings = dao.fetchPendingBookings(userId);

            if (bookings == null || bookings.isEmpty()) {
                showNoDataMessage(container, "No pending booking requests found.");
            } else {
                displayBookingCards(container, bookings);
            }

        } catch (Exception e) {
            showError(container, "Error loading bookings: " + e.getMessage());
            e.printStackTrace();
        }

        // Refresh the UI
        container.revalidate();
        container.repaint();
    }

    /**
     * Display a message when no bookings are found
     */
    private void showNoDataMessage(JPanel container, String message) {
        container.setLayout(new GridBagLayout());
        JLabel noDataLabel = new JLabel(message);
        noDataLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        noDataLabel.setForeground(Color.GRAY);
        container.add(noDataLabel);
    }

    /**
     * Display a message when an error occurs
     */
    private void showError(JPanel container, String message) {
        container.setLayout(new FlowLayout());
        JLabel errorLabel = new JLabel(message);
        errorLabel.setForeground(Color.RED);
        container.add(errorLabel);
    }

    /**
     * Creates and adds booking cards to the container
     */
    private void displayBookingCards(JPanel container, List<company_bookingmanagementModel> bookings) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        for (company_bookingmanagementModel b : bookings) {
            company_bookingmanagementunit unit = new company_bookingmanagementunit(
                    b.getPayId(),    // int
                    b.getBookId(),   // int
                    b.getVehId(),    // int
                    b.getName(),     // String
                    b.getAddress(),  // String
                    b.getPhone(),    // String
                    b.getStart(),    // String
                    b.getEnd(),      // String
                    b.getDays(),     // int
                    b.getRate(),     // double
                    b.getTotal(),    // double
                    b.getReceipt(),  // byte[]
                    b.getLicense()   // byte[]
            );

            unit.setAlignmentX(Component.CENTER_ALIGNMENT);
            container.add(unit);
            container.add(Box.createVerticalStrut(15)); // spacing between cards
        }

        container.add(Box.createVerticalGlue()); // push cards to top
    }

}
