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

       public RegistrationResult registerSeeker(String fullName, String user, String contact, 
                                        String email, String address, String pass, 
                                        String rePass, String security) {
    if (!pass.equals(rePass)) {
        return new RegistrationResult(false, "Passwords do not match.");
    }

   
    seekerRegisterModel seeker = new seekerRegisterModel(fullName, user, contact, email, address, pass, security);

    
    try {
        boolean success = seekerDao.registerSeeker(seeker);
        return success ? new RegistrationResult(true, "Success!") : new RegistrationResult(false, "Failed.");
    } catch (Exception e) {
        return new RegistrationResult(false, "Database error.");
    }
}
}

