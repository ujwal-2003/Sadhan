/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.companyRegisterDao;
import util.RegistrationResult;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author prachisilwal
 */
public class companyRegisterController {
    // Single instance of DAO to be used by all methods
    private companyRegisterDao companyDao = new companyRegisterDao();

    public RegistrationResult registerCompany(String name, String user, String contact, 
                                              String email, String address, String pass, 
                                              String rePass, String security, String qrPath) {
        
        // 1. Validation logic
        if (!pass.equals(rePass)) {
            return new RegistrationResult(false, "Passwords do not match!");
        }

        // 2. Convert path to byte[] for BLOB storage
        byte[] qrData = null;
        if (qrPath != null && !qrPath.isEmpty()) {
            try {
                qrData = Files.readAllBytes(Paths.get(qrPath));
            } catch (Exception e) {
                return new RegistrationResult(false, "Failed to process QR Image: " + e.getMessage());
            }
        }

        // 3. Call the DAO instance defined at the class level
        return companyDao.saveCompany(name, user, contact, email, address, pass, security, qrData);
    }
}
 

