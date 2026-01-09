/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.admin_companylistDao;
import java.util.List;
import model.admin_companylistModel;
/**
 *
 * @author hp
 */
public class admin_companylistController {

    private final admin_companylistDao dao;

    public admin_companylistController() {
        this.dao = new admin_companylistDao();
    }

    /**
     * Get companies by search query
     * @param query the search string
     * @return List of companies
     */
    public List<admin_companylistModel> getCompanies(String query) {
        return dao.fetchCompanies(query);
    }
}
