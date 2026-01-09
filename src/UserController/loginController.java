/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.loginDao;
import javax.swing.JOptionPane;
import view.AdminDashboard;
import view.CompanyDashboard;
import view.login;
import view.UserDashboard;
import model.loginModel; // Ensure this import is here
/**
 *
 * @author hp
 */
public class loginController {
    // 1. Declare these fields so the methods can see them
    private login view;
    private loginDao dao;

    // 2. The constructor must initialize these fields
    public loginController(login view) {
        this.view = view;
        this.dao = new loginDao();
    }
    // Inside loginController.java
    public int authenticateCompany(String username, String password) {
       return dao.getCompanyId(username, password); 
    }

    public int authenticateSeeker(String username, String password) {
       return dao.getSeekerId(username, password);
    }
    public int authenticateAdmin(String username, String password) {
    // This calls your DAO to check the database
    // Ensure 'dao' is already initialized in your controller constructor
    return dao.getadminId(username, password); 
}
    

    public void loginUser() {
        String username = view.getUsernameField().getText().trim();
        String password = new String(view.getPasswordField().getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please enter all credentials.");
            return;
        }

        // 3. Create the model instance using your good model
        loginModel userModel = new loginModel(username, password);

        // 4. Validate credentials and get role
        String role = dao.validateUserAndGetRole(userModel.getUsername(), userModel.getPassword());
        
        if (role != null) {
            java.awt.EventQueue.invokeLater(() -> {
                String normalizedRole = role.trim(); 
                
                if (normalizedRole.equalsIgnoreCase("Admin")) {
                    new AdminDashboard().setVisible(true);
                } 
                else if (normalizedRole.equalsIgnoreCase("Company")) {
                    int companyId = authenticateCompany(userModel.getUsername(), userModel.getPassword()); 
                    if (companyId != -1) {
                        new CompanyDashboard(companyId).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(view, "Error retrieving Company ID.");
                    }
                } 
                else if (normalizedRole.equalsIgnoreCase("User")) {
                    int seekerId = authenticateSeeker(userModel.getUsername(), userModel.getPassword());
                    if (seekerId != -1) {
                        new UserDashboard(seekerId).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(view, "Error retrieving User ID.");
                    }
                }
                view.dispose();
            });
        } else {
            JOptionPane.showMessageDialog(view, "Invalid Username or Password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
  }
  }

