/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.UserHistoryDao;
import model.UserHistoryModel;
import view.UserHistory;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;

/**
 *
 * @author hp
 */
public class UserHistoryController {
    
    private UserHistory view;
    private UserHistoryDao dao;

    public UserHistoryController(UserHistory view) {
        this.view = view;
        this.dao = new UserHistoryDao();
    }

    public void loadTableData(int userId) {
        try {
            List<UserHistoryModel> data = dao.getUserHistory(userId);
            DefaultTableModel model = (DefaultTableModel) view.getHistoryTable().getModel();
            model.setRowCount(0);

            for (UserHistoryModel h : data) {
                model.addRow(new Object[]{
                    h.getCustomerId(), h.getVehicleId(), h.getCompanyId(), h.getModel(),
                    h.getPrice(), h.getStartDate(), h.getEndDate(), h.getTotalDays(),
                    h.getTotalPrice(), h.getStatus()
                });
            }
        } catch (Exception e) {
            view.showError("Error loading history: " + e.getMessage());
        }
    }

    public void applyFilter(String query) {
        var sorter = view.getSorter();
        if (query == null || query.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Index 3=Model, 5=Start Date, 6=End Date
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query, 3, 5, 6));
        }
    }
    
}
