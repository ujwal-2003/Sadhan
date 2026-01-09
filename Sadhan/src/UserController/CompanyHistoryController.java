/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.CompanyHistoryDao;
import model.CompanyHistoryModel;
import view.CompanyHistory; // Ensure this matches your View filename
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.RowFilter; // Also ensure this is there for the filtering
/**
 *
 * @author hp
 */
public class CompanyHistoryController {
    private CompanyHistory view;
    private CompanyHistoryDao dao;

    public CompanyHistoryController(CompanyHistory view) {
        this.view = view;
        this.dao = new CompanyHistoryDao();
    }

    public void loadTableData(int companyId) {
        try {
            List<CompanyHistoryModel> data = dao.fetchHistory(companyId);
            DefaultTableModel model = (DefaultTableModel) view.getHistoryTable().getModel();
            model.setRowCount(0);

            for (CompanyHistoryModel h : data) {
                model.addRow(new Object[]{
                    h.getCompanyId(), h.getVehicleId(), h.getCustomerId(), h.getFullName(),
                    h.getContactNo(), h.getAddress(), h.getStartDate(), h.getEndDate(),
                    h.getPrice(), h.getTotalDays(), h.getTotalPrice()
                });
            }
        } catch (Exception e) {
            view.showError("Error loading history: " + e.getMessage());
        }
    }
      public void applyFilter(String query) {
    var sorter = view.getSorter();
    if (sorter == null) return;

    if (query == null || query.trim().isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        try {
            // Index 3 = User Name
            // Index 6 = Start Date
            // Index 7 = End Date
            // This will show the row if the text matches ANY of these columns
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query.trim(), 3, 6, 7));
        } catch (java.util.regex.PatternSyntaxException e) {
            System.err.println("Bad regex: " + e.getMessage());
        }
    }
}
   
    
}
