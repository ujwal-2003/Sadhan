/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author prachisilwal
 */

public class seekerRegisterModel {
    private String fullName;
    private String username;
    private String contact;
    private String email;
    private String address;
    private String password;
    private String securityAnswer;

    public seekerRegisterModel(String fullName, String username, String contact, String email,
                String address, String password, String securityAnswer) {
        this.fullName = fullName;
        this.username = username;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
        this.securityAnswer = securityAnswer;
    }
    
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public String getSecurityAnswer() { return securityAnswer; }
}
