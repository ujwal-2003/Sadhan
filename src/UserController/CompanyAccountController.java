/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.CompanyAccountDao;
import model.CompanyAccount;
import util.ManageAccount;

public class CompanyAccountController {

    private final CompanyAccountDao dao = new CompanyAccountDao();

    public CompanyAccount load(String username) {
        try {
            return dao.getByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    public ManageAccount update(CompanyAccount company) {

        if (company.getCompanyName().isEmpty()
                || company.getContact().isEmpty()
                || company.getEmail().isEmpty()
                || company.getAddress().isEmpty()) {
            return new ManageAccount(false, "All fields are required");
        }

        try {
            boolean updated = dao.update(company);
            if (updated) {
                return new ManageAccount(true, "Company profile updated successfully");
            } else {
                return new ManageAccount(false, "Update failed");
            }
        } catch (Exception e) {
            return new ManageAccount(false, "Database error while updating company profile");
        }
    }
}
