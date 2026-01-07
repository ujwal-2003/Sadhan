/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.CompanyDao;
import model.Company;
import util.RegistrationResult;
import java.sql.SQLException;
/**
 *
 * @author prachisilwal
 */
public class CompanyController {
    private CompanyDao companyDao = new CompanyDao();
    
 public RegistrationResult registerCompany(
            String name,
            String username,
            String contact,
            String email,
            String address,
            String password,
            String rePassword,
            String securityAnswer) {

        if (name.isBlank() || username.isBlank() || contact.isBlank() ||
            email.isBlank() || address.isBlank() || password.isBlank() ||
            rePassword.isBlank() || securityAnswer.isBlank()) {

            return new RegistrationResult(false, "All fields are required!");
        }

        if (!password.equals(rePassword)) {
            return new RegistrationResult(false, "Passwords do not match!");
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return new RegistrationResult(false, "Invalid email format!");
        }

        if (!contact.matches("\\d{10}")) {
            return new RegistrationResult(false, "Contact number must be 10 digits!");
        }

        Company company = new Company(
                name.trim(),
                username.trim(),
                contact.trim(),
                email.trim(),
                address.trim(),
                password,
                securityAnswer.trim()
        );

        try {
            boolean success = companyDao.registerCompany(company);

            if (success) {
                return new RegistrationResult(true, "Company registered successfully!");
            } else {
                return new RegistrationResult(false, "Registration failed.");
            }

        } catch (SQLException e) {
            if (e.getMessage().contains("Duplicate")) {
                return new RegistrationResult(false, "Username or email already exists!");
            }
            return new RegistrationResult(false, "Database error occurred.");
        }
    }
}
