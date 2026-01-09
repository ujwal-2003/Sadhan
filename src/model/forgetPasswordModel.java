/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hp
 */
public class forgetPasswordModel {
    private String username;
    private String email;
    private String contact;
    private String securityAnswer;
    private String newPassword;
    
    // Constructor
    public forgetPasswordModel(String username, String email, String contact, String securityAnswer, String newPassword) {
        this.username = username;
        this.email = email;
        this.contact = contact;
        this.securityAnswer = securityAnswer;
        this.newPassword = newPassword;
    }

    // Getters
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
    public String getSecurityAnswer() { return securityAnswer; }
    public String getNewPassword() { return newPassword; }
    
}
