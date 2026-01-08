/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.EarningReportDao;
import model.EarningReportModel;
import view.EarningReport;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import java.text.DecimalFormat;

/**
 *
 * @author hp
 */
public class EarningReportController {
    private EarningReport view;
    private EarningReportDao dao;
    private DecimalFormat df = new DecimalFormat("'Rs.' #,##0.00");

    public EarningReportController(EarningReport view) {
        this.view = view;
        this.dao = new EarningReportDao();
    }

    public void refreshReport(int companyId) {
        try {
            // Update Table
            List<EarningReportModel> data = dao.getReportData(companyId);
            DefaultTableModel model = (DefaultTableModel) view.getReportTable().getModel();
            model.setRowCount(0);
            for (EarningReportModel m : data) {
                model.addRow(new Object[]{
                    m.getVehicleId(), m.getBookingId(), m.getStartDate(), m.getEndDate(),
                    m.getNoOfDays(), "Rs. " + m.getPricePerDay(), "Rs. " + m.getTotalPrice()
                });
            }

            // Update Summary Labels
            Map<String, Object> summary = dao.getSummaryData(companyId);
            view.getBookingCountLabel().setText(summary.get("count").toString());
            view.getTotalEarningLabel().setText(df.format(summary.get("total")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
