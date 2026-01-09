/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Blob;

/**
 *
 * @author hp
 */

public class UserDashboardModel {
    private int id;
    private int companyId;
    private String brand;
    private String model;
    private String type;
    private String colour;
    private String numberPlate;
    private double price;
    private String availabilityStatus;
    private byte[] thumbnailBytes; // Used for the small grid image
    private Blob imageFrontBlob;   // Used for the large popup image
    private Blob imageSideBlob;

    // Helper method to combine Brand and Model
    public String getFullName() { 
        return (brand != null ? brand : "") + " " + (model != null ? model : ""); 
    }

    // Standard Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }
    public String getNumberPlate() { return numberPlate; }
    public void setNumberPlate(String numberPlate) { this.numberPlate = numberPlate; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String status) { this.availabilityStatus = status; }
    public byte[] getThumbnailBytes() { return thumbnailBytes; }
    public void setThumbnailBytes(byte[] bytes) { this.thumbnailBytes = bytes; }
    public Blob getImageFrontBlob() { return imageFrontBlob; }
    public void setImageFrontBlob(Blob blob) { this.imageFrontBlob = blob; }
    public Blob getImageSideBlob() { return imageSideBlob; }
    public void setImageSideBlob(Blob blob) { this.imageSideBlob = blob; }
}

