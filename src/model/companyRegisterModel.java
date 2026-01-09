/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author prachisilwal
 */
public class companyRegisterModel {
    private String companyName;
    private String username;
    private String contact;
    private String email;
    private String address;
    private String password;
    private String securityQuestion;
    private String qrPath;

    // Constructor
    public companyRegisterModel(String companyName, String username, String contact, String email, 
                        String address, String password, String securityQuestion, String qrPath) {
        this.companyName = companyName;
        this.username = username;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.qrPath = qrPath;
    }

    // Getters and Setters
    public String getCompanyName() { return companyName; }
    public String getUsername() { return username; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public String getSecurityQuestion() { return securityQuestion; }
    public String getQrPath() { return qrPath; }
}

