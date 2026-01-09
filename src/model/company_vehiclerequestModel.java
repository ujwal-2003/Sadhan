/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.File;
/**
 *
 * @author hp
 */
public class company_vehiclerequestModel {

    private int companyId;
    private String brand;
    private String model;
    private String type;
    private String colour;
    private String numberPlate;
    private double price;
    private File frontImage;
    private File sideImage;

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public File getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(File frontImage) {
        this.frontImage = frontImage;
    }

    public File getSideImage() {
        return sideImage;
    }

    public void setSideImage(File sideImage) {
        this.sideImage = sideImage;
    }
}
