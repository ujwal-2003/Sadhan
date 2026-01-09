/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.company_vehiclerequestDao;
import model.company_vehiclerequestModel;
import database.MySqlConnection;
import java.sql.Connection;

/**
 *
 * @author hp
 */
public class company_vehiclerequestController {

    private final company_vehiclerequestDao dao;

    public company_vehiclerequestController() {
        // DAO will use a connection per operation
        this.dao = new company_vehiclerequestDao();
    }

    /**
     * Submits a vehicle request.
     * @param request The vehicle request model
     * @return SUCCESS if saved, or error message
     */
    public String submitRequest(company_vehiclerequestModel request) {
        // Basic validation
        if (request.getNumberPlate() == null || request.getNumberPlate().isEmpty()) {
            return "Number plate is required!";
        }

        if (request.getBrand() == null || request.getBrand().isEmpty()) {
            return "Brand is required!";
        }

        if (request.getModel() == null || request.getModel().isEmpty()) {
            return "Model is required!";
        }

        if (request.getPrice() <= 0) {
            return "Price must be greater than zero!";
        }

        // Database operation
        try (Connection conn = MySqlConnection.getInstance().getConnection()) {
            // Check for duplicate number plate
            if (dao.isPlateExists(conn, request.getNumberPlate())) {
                return "Vehicle with this number plate already exists!";
            }

            // Insert the vehicle request
            boolean saved = dao.insertVehicleRequest(request);

            return saved ? "SUCCESS" : "Failed to save vehicle request";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
