/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.admin_VehicleRequestDao;
import model.admin_VehicleRequestModel;
import view.admin_VehicleRequest;
import view.admin_vehicledetails;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author hp
 */
public class admin_VehicleRequestController {

    private admin_VehicleRequest view;
    private admin_VehicleRequestDao dao;
    private String lastSearchText = ""; 

    public admin_VehicleRequestController(admin_VehicleRequest view) {
        this.view = view;
        this.dao = new admin_VehicleRequestDao();
    }

    public void refreshData(String searchInput) {
        // Handle null and trim
        lastSearchText = (searchInput == null) ? "" : searchInput.trim();
        
        try {
            // If it's the placeholder, treat as empty search
            String query = (lastSearchText.equalsIgnoreCase("search vehicles")) ? "" : lastSearchText;
            List<admin_VehicleRequestModel> requests = dao.getPendingRequests(query);
            view.populateTable(requests);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Data Fetch Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleViewDetails(String plateNumber, admin_VehicleRequest mainFrame) {
        try (ResultSet rs = dao.getVehicleFullDetails(plateNumber)) {
            if (rs.next()) {
                admin_vehicledetails detailsPanel = new admin_vehicledetails();
                
                // Pass 'this' controller so the panel buttons work
                detailsPanel.setParentFrame(mainFrame, this);

                File frontImg = saveBlobToTempFile(rs.getBlob("image_front"), "front_" + plateNumber);
                File sideImg = saveBlobToTempFile(rs.getBlob("image_side"), "side_" + plateNumber);

                detailsPanel.setVehicleData(
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("colour"),
                        plateNumber,
                        "Rs. " + rs.getString("price") + "/Day",
                        frontImg,
                        sideImg
                );

                JFrame detailWindow = new JFrame("Review Vehicle: " + plateNumber);
                detailWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                detailWindow.add(detailsPanel);
                detailWindow.pack();
                detailWindow.setSize(940, 700);
                detailWindow.setLocationRelativeTo(null);
                detailWindow.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error fetching details: " + e.getMessage());
        }
    }

    private File saveBlobToTempFile(Blob blob, String prefix) throws Exception {
        if (blob == null) return null;
        File tempFile = File.createTempFile(prefix, ".jpg");
        tempFile.deleteOnExit();
        try (InputStream is = blob.getBinaryStream(); FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }

    public void approveVehicle(String plate, admin_VehicleRequest frame) {
        int response = JOptionPane.showConfirmDialog(frame, "Approve this vehicle?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (response != JOptionPane.YES_OPTION) return;

        try {
            if (dao.updateVehicleStatus(plate, "approved", null)) {
                JOptionPane.showMessageDialog(null, "Vehicle approved.");
                closeAndRefresh(frame, plate);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    public void rejectVehicle(String plate, admin_VehicleRequest frame) {
        String reason = JOptionPane.showInputDialog(frame, "Enter rejection reason:");
        if (reason == null || reason.trim().isEmpty()) return;

        try {
            if (dao.updateVehicleStatus(plate, "rejected", reason.trim())) {
                JOptionPane.showMessageDialog(null, "Vehicle rejected.");
                closeAndRefresh(frame, plate);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    /**
     * IMPROVED: Specifically targets the detail window for closing 
     * without disposing of the main dashboard.
     */
    private void closeAndRefresh(admin_VehicleRequest frame, String plate) {
        // 1. Find the Detail Window that matches this plate and close ONLY that
        for (java.awt.Window w : java.awt.Window.getWindows()) {
            if (w instanceof JFrame) {
                JFrame jf = (JFrame) w;
                if (jf.getTitle().contains(plate)) {
                    jf.dispose();
                    break; 
                }
            }
        }

        // 2. Refresh the main list
        if (frame != null) {
            frame.requestFocus();
            refreshData(lastSearchText);
        }
    }
}




