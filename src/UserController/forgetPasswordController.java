/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.forgetPasswordDao;

/**
 *
 * @author hp
 */
public class forgetPasswordController {
     private forgetPasswordDao dao;
     
     public forgetPasswordController() {
        this.dao = new forgetPasswordDao();
    }
    // Inside forgetPasswordController.java
public String handlePasswordReset(String user, String email, String contact, String answer, String pass, String confirmPass) {
     user = user.trim();
    email = email.trim();
    contact = contact.trim();
    answer = answer.trim();
    // 1. Check for empty fields
    if (user.isEmpty() || email.isEmpty() || contact.isEmpty() || answer.isEmpty() || pass.isEmpty()) {
        return "All fields are required.";
    }

    // 2. Validate Password Confirmation
    // This prevents the DAO from running unnecessary queries if the user mistyped the new password
    if (!pass.equals(confirmPass)) {
        return "Passwords do not match. Please try again.";
    }

    // 3. Optional: Add password length validation
    if (pass.length() < 6) {
        return "Password must be at least 6 characters long.";
    }

    // 4. Call the DAO (which now checks Admin, Company, and Customer tables)
    boolean isUpdated = dao.resetPassword(user, email, contact, answer, pass);

    // 5. Final Result
    if (isUpdated) {
        return "Success";
    }  else {
    // A slightly more helpful message since you have Admin, Company, and User tables
    return "Verification failed. Please ensure the details match your registered account type and security answer.";
    }
}

}
