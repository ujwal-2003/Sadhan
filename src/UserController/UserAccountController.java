/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import model.UserAccount;
import dao.UserAccountDao;
import util.ManageAccount;

/**
 *
 * @author prachisilwal
 */
public class UserAccountController {
    private final UserAccountDao dao = new UserAccountDao();

    public UserAccount load(String username) {
        try {
            return dao.getByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    public ManageAccount update(UserAccount user) {

        if (user.getFullName().isEmpty()
                || user.getContact().isEmpty()
                || user.getEmail().isEmpty()
                || user.getAddress().isEmpty()) {
            return new ManageAccount(false, "All fields are required");
        }

        try {
            boolean updated = dao.update(user);
            if (updated) {
                return new ManageAccount(true, "Profile updated successfully");
            } else {
                return new ManageAccount(false, "Update failed");
            }
        } catch (Exception e) {
            return new ManageAccount(false, "Database error while updating profile");
        }
    }
}
