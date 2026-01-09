/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class company_vehiclestatusModel {
    private String fullName;
    private String plate;
    private String status;

    public company_vehiclestatusModel(String fullName, String plate, String status) {
        this.fullName = fullName;
        this.plate = plate;
        this.status = status;
    }

    public String getFullName() { return fullName; }
    public String getPlate() { return plate; }
    public String getStatus() { return status; }
}