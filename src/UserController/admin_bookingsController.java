/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;
import dao.admin_bookingsDAO;
import model.admin_bookingsModel;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author prachisilwal
 */
public class admin_bookingsController {   
    private admin_bookingsDAO dao;

    public admin_bookingsController() {
        this.dao = new admin_bookingsDAO();
    }

    public void populateTable(javax.swing.JTable table, String query) {
        List<admin_bookingsModel> bookings = dao.searchBookings(query);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear current table rows

        for (admin_bookingsModel b : bookings) {
            // Updated row to include b.getTotalDays()
            model.addRow(new Object[]{
                b.getCompanyId(), 
                b.getVehicleId(), 
                b.getUserId(),
                b.getCompanyName(), 
                b.getUserName(), 
                b.getLocation(),
                b.getVehicleName(), 
                b.getStartDate(), 
                b.getEndDate(),
                b.getTotalDays(), // <--- New column data
                b.getPricePerDay(), 
                b.getTotalPrice()
            });
        }
}
    
}
