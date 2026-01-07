/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import util.RegistrationResult;
import dao.SeekerDao;

import model.Seeker;
/**
 *
 * @author prachisilwal
 */
public class SeekerController {
    private SeekerDao seekerDao = new SeekerDao();

    public RegistrationResult registerSeeker(
            String fullName,
            String username,
            String contact,
            String email,
            String address,
            String password,
            String rePassword,
            String securityAnswer) {

        
        if (fullName.isBlank() || username.isBlank() || contact.isBlank()
                || email.isBlank() || address.isBlank()
                || password.isBlank() || rePassword.isBlank()
                || securityAnswer.isBlank()) {

            return new RegistrationResult(false, "All fields are required.");
        }

        if (!password.equals(rePassword)) {
            return new RegistrationResult(false, "Passwords do not match.");
        }
        
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return new RegistrationResult(false, "Invalid email format.");
        }

        if (!contact.matches("\\d{10}")) {
            return new RegistrationResult(false, "Contact number must be 10 digits.");
        }

        Seeker seeker = new Seeker(
                fullName, username, contact, email,
                address, password, securityAnswer
        );

        try {
            boolean success = seekerDao.registerSeeker(seeker);

            if (success) {
                return new RegistrationResult(true, "Seeker registered successfully!");
            } else {
                return new RegistrationResult(false, "Registration failed.");
            }

        } catch (Exception e) {
            if (e.getMessage().contains("Duplicate")) {
                return new RegistrationResult(false, "Username or email already exists.");
            }
            return new RegistrationResult(false, "Database error.");
        }
    }
}