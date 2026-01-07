/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author hp
 */
public class admin_VehicleRequestModel {
    private String fullName;
    private String plate;
    private String price;
    private String companyId;

    public admin_VehicleRequestModel(String fullName, String plate, String price, String companyId) {
        this.fullName = fullName;
        this.plate = plate;
        this.price = price;
        this.companyId = companyId;
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getPlate() { return plate; }
    public String getPrice() { return price; }
    public String getCompanyId() { return companyId; }
}