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
    
    public RegistrationResult registerCompany(String name, String user, String contact, 
                                              String email, String address, String pass, 
                                              String rePass, String security, String qrPath) {

        if (!pass.equals(rePass)) {
            return new RegistrationResult(false, "Passwords do not match!");
        }

         byte[] qrData = null;
        if (qrPath != null && !qrPath.isEmpty()) {
            try {
                qrData = Files.readAllBytes(Paths.get(qrPath));
            } catch (Exception e) {
                return new RegistrationResult(false, "Failed to process QR Image: " + e.getMessage());
            }
        }

       }

 return CompanyDao.saveCompany(name, user, contact, email, address, pass, security, qrData);
    }
}
