/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author prachisilwal
 */
public class Company {
    private String name, username, contact, email, address, password, securityAnswer;

    // Constructor, getters and setters
    public Company(String name, String username, String contact, String email, String address, String password, String securityAnswer) {
        this.name = name;
        this.username = username;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
        this.securityAnswer = securityAnswer;
    }

    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPassword() { return password; }
    public String getSecurityAnswer() { return securityAnswer; }
}

