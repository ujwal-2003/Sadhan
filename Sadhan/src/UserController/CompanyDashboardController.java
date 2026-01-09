/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.CompanyDashboardDao;
import model.CompanyDashboardModel;
import view.CompanyDashboard;
import view.login;
import view.CompanyHistory;
import view.EarningReport;
import java.util.List;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author hp
 */
public class CompanyDashboardController {
    private CompanyDashboard view;
    private CompanyDashboardDao dao;

    public CompanyDashboardController(CompanyDashboard view) {
        this.view = view;
        this.dao = new CompanyDashboardDao();
    }

    public void refreshTable(int companyId) {
        new Thread(() -> {
            try {
                // Fetch list from DAO using the Model
                List<CompanyDashboardModel> bookings = dao.getBookedVehicles(companyId);
                
                // Switch to Event Dispatch Thread to update the UI components
                java.awt.EventQueue.invokeLater(() -> {
                    updateViewTable(bookings);
                });
            } catch (Exception e) {
                System.out.println("Error loading table: " + e.getMessage());
            }
        }).start();
    }
    private void updateViewTable(List<CompanyDashboardModel> data) {
        DefaultTableModel model = (DefaultTableModel) view.getBookingTable().getModel();
        model.setRowCount(0); // Clear existing rows
        
        for (CompanyDashboardModel b : data) {
            model.addRow(new Object[]{
                b.getVehicleId(), 
                b.getModel(), 
                b.getCustomerId(), 
                b.getBookingId(),
                b.getCustomerName(), 
                b.getStartDate(), 
                b.getEndDate(),
                b.getPricePerDay(), 
                b.getTotalPrice(), 
                b.getStatus()
            });
        }
    }
      public void handleVehicleReturn(int vId, int bId, int companyId) {
        try {
            // 1. Check DAO method name (must match CompanyDashboardDao.java)
            if (dao.completeBookingTransaction(vId, bId)) {
                
                // 2. Check View method name (must be public in CompanyDashboard.java)
                view.showSuccess("Success! Vehicle returned.");
                
                // 3. Check local method name (must match the refresh method below)
                refreshTable(companyId);
            } else {
                view.showError("Update failed.");
            }
        } catch (Exception e) {
            view.showError("Database Error: " + e.getMessage());
        }
    }
    
     private void refreshEarningsReport() {
        try {
            EarningReport report = EarningReport.getInstance();
            if (report != null) {
                report.refreshReport();
            }
        } catch (Exception e) {
            // Fail silently if report window isn't open
        }
    }

    // --- Navigation Methods ---

    public void handleLogout() {
        login loginPage = new login();
        loginPage.setVisible(true);
        view.dispose();
    }

    public void openHistory(int companyId) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Rental History - Company View");
        CompanyHistory historyPanel = new CompanyHistory(companyId); 
        frame.add(historyPanel);
        frame.pack();
        frame.setLocationRelativeTo(view);
        frame.setVisible(true);
    }
}
