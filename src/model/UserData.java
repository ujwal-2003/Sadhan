/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class UserData {
       private String model, brand, type, colour, numberPlate, price;
       private byte[] frontImage;
       private byte[] sideImage;


    public UserData(String model, String brand, String type,
                    String colour, String numberPlate, String price,byte[] frontImage, byte[] sideImage) {
        this.model = model;
        this.brand = brand;
        this.type = type;
        this.colour = colour;
        this.numberPlate = numberPlate;
        this.price = price;
        this.frontImage = frontImage;
        this.sideImage = sideImage;
    }

    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public String getVehicletype() { return type; }
    public String getColour() { return colour; }
    public String getNumberPlate() { return numberPlate; }
    public String getPrice() { return price; }
    public byte[] getFrontImage() {return frontImage;}
    public byte[] getSideImage() {return sideImage;}
    
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setVehicletype(String type) { this.type = type; }
    public void setColour(String colour) { this.colour = colour; }
    public void setNumberPlate(String numberPlate) { this.numberPlate = numberPlate; }
    public void setPrice(String price) { this.price = price; }

    public void setFrontImage(byte[] frontImage) { this.frontImage = frontImage; }
    public void setSideImage(byte[] sideImage) { this.sideImage = sideImage; }



  
   

 
    
}
