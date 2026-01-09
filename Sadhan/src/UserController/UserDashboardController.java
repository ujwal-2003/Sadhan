/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.UserDashboardDao;
import model.UserDashboardModel;
import view.UserDashboard;
import java.util.List;
import java.io.*;
import java.sql.Blob;
/**
 *
 * @author hp
 */
public class UserDashboardController {
    private UserDashboard view;
    private UserDashboardDao dao;

    public UserDashboardController(UserDashboard view) {
        this.view = view;
        this.dao = new UserDashboardDao();
    }

    public void processWelcomeMessage(int userId) {
        try {
            String name = dao.getCustomerName(userId);
            view.updateWelcomeLabel(name != null ? name.toUpperCase() : "USER");
        } catch (Exception e) {
            view.updateWelcomeLabel("OFFLINE MODE");
        }
    }

    public void fetchVehicles(String searchText) {
        try {
            List<UserDashboardModel> vehicles = dao.getApprovedVehicles(searchText);
            if (vehicles.isEmpty()) {
                view.showEmptyState(searchText);
            } else {
                view.renderVehicleGrid(vehicles);
            }
        } catch (Exception e) {
            view.showError("Database Error: " + e.getMessage());
        }
    }

    public void openVehicleDetails(int vehicleId) {
        try {
            UserDashboardModel vehicle = dao.getVehicleFullDetails(vehicleId);
            if (vehicle != null) {
                File frontImg = saveBlobToTemp(vehicle.getImageFrontBlob(), "front_" + vehicleId);
                File sideImg = saveBlobToTemp(vehicle.getImageSideBlob(), "side_" + vehicleId);
                view.showBookingPopup(vehicle, frontImg, sideImg);
            }
        } catch (Exception e) {
            view.showError("Could not load vehicle specifications.");
        }
    }

    private File saveBlobToTemp(Blob blob, String prefix) throws Exception {
        if (blob == null) return null;
        File tempFile = File.createTempFile(prefix + "_", ".jpg");
        tempFile.deleteOnExit();
        try (InputStream is = blob.getBinaryStream();
             FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }
        return tempFile;
    }
}
