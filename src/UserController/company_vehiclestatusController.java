/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.company_vehiclestatusDao;
import model.company_vehiclestatusModel;
import view.company_vehiclestatusdetail;
import view.company_vehicleStatuslist;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;

/**
 *
 * @author hp
 */
public class company_vehiclestatusController {
    private company_vehicleStatuslist view; 
    private company_vehiclestatusDao dao;
    
    public company_vehiclestatusController(company_vehicleStatuslist view) {
        this.view = view;
        this.dao = new company_vehiclestatusDao();
    }

    public void refreshList(int companyId, String searchInput) {
        try {
            String term = (searchInput == null || searchInput.isEmpty() || searchInput.equals("search your vehicle")) 
                          ? "" : searchInput.trim();
            
            List<company_vehiclestatusModel> vehicles = dao.getVehicleRequests(companyId, term);
            view.updateUI(vehicles);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Data Error: " + e.getMessage());
        }
    }

    public void handleViewDetails(String plate, Component parent) {
        try (ResultSet rs = dao.getVehicleByPlate(plate)) {
            if (rs.next()) {
                company_vehiclestatusdetail detailPanel = new company_vehiclestatusdetail();
                detailPanel.setController(this); // Linked for deletion logic

                File front = saveBlob(rs.getBlob("image_front"), "front_" + plate);
                File side = saveBlob(rs.getBlob("image_side"), "side_" + plate);

                String status = rs.getString("status");
                String reason = "rejected".equalsIgnoreCase(status) ? rs.getString("rejection_reason") : "Status: " + status;

                detailPanel.setVehicleData(
                    rs.getString("brand"), rs.getString("model"), rs.getString("type"),
                    rs.getString("colour"), plate, rs.getString("price"),
                    status, (reason == null ? "" : reason), front, side
                );

                JFrame popup = new JFrame("Vehicle Details - " + plate);
                popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                popup.getContentPane().add(detailPanel);
                popup.pack();
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent, "System Error: " + e.getMessage());
        }
    }

    private File saveBlob(Blob blob, String prefix) throws Exception {
        if (blob == null) return null;
        File tempFile = File.createTempFile(prefix, ".jpg");
        tempFile.deleteOnExit();
        try (InputStream is = blob.getBinaryStream(); FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = is.read(buffer)) != -1) fos.write(buffer, 0, read);
        }
        return tempFile;
    }

    public void processDelete(String plate, java.awt.Component parentComponent) {
        int confirm = JOptionPane.showConfirmDialog(parentComponent, 
                "Are you sure you want to permanently remove vehicle " + plate + "?", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (dao.deleteVehicle(plate)) {
                    JOptionPane.showMessageDialog(parentComponent, "Vehicle removed successfully.");
                    
                    java.awt.Window win = javax.swing.SwingUtilities.getWindowAncestor(parentComponent);
                    if (win != null) win.dispose();
                    
                    if (view != null) { // Fixed variable name
                        view.triggerRefresh();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parentComponent, "Error deleting: " + e.getMessage());
            }
        }
    }
}