/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import util.RegistrationResult;
import dao.seekerRegisterDao;

import model.seekerRegisterModel;
/**
 *
 * @author prachisilwal
 */
public class seekerRegisterController {
    private seekerRegisterDao seekerDao = new seekerRegisterDao();

   public RegistrationResult registerSeeker(String fullName, String user, String contact, 
                                        String email, String address, String pass, 
                                        String rePass, String security) {
    // 1. Validation (Example: check if passwords match)
    if (!pass.equals(rePass)) {
        return new RegistrationResult(false, "Passwords do not match.");
    }

    // 2. Instantiate the Model
    seekerRegisterModel seeker = new seekerRegisterModel(fullName, user, contact, email, address, pass, security);

    // 3. Pass the Model to the DAO
    try {
        boolean success = seekerDao.registerSeeker(seeker);
        return success ? new RegistrationResult(true, "Success!") : new RegistrationResult(false, "Failed.");
    } catch (Exception e) {
        return new RegistrationResult(false, "Database error.");
    }
}
}