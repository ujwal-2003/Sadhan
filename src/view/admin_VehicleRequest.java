/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author hp
 */
public class VehicleRequest extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VehicleRequest.class.getName());

    /**
     * Creates new form VehicleRequest
     */
    public VehicleRequest() {
        initComponents();
        // 1. UI Layout Setup
    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));
    jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

    // 2. Initialize Events (Only once!)
    addSearchEvents();

    // 3. Smart Refresh Logic
    this.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
        @Override
        public void windowGainedFocus(java.awt.event.WindowEvent e) {
            String currentSearch = jTextField1.getText().trim();
            // Ensure this string matches your jTextField1 default text exactly
            if (currentSearch.isEmpty() || currentSearch.equals("search vehicles")) {
                loadRequests("");
            }
        }
        @Override
        public void windowLostFocus(java.awt.event.WindowEvent e) {}
    });

    // Note: We removed the manual loadRequests("") here because 
    // windowGainedFocus will trigger automatically when the frame opens.

    }
  public final void loadRequests(String query) {
    jPanel2.removeAll();
    // Use BoxLayout so items stack vertically
    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

    // Updated SQL to include Plate and Company ID search
    String sql = "SELECT brand, model, numberPlate, price, company_id FROM vehicleDetails " +
                 "WHERE status = 'pending' AND (" +
                 "brand LIKE ? OR " +
                 "model LIKE ? OR " +
                 "numberPlate LIKE ? OR " +
                 "company_id LIKE ?)";

    try (Connection conn = MySqlConnection.getInstance().getConnection();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        String searchPattern = "%" + query + "%";
        // Map the searchPattern to all 4 placeholders
        pstmt.setString(1, searchPattern);
        pstmt.setString(2, searchPattern);
        pstmt.setString(3, searchPattern);
        pstmt.setString(4, searchPattern);
        
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String fullName = rs.getString("brand") + " " + rs.getString("model");
            String plate = rs.getString("numberPlate");
            String vehiclePrice = rs.getString("price");
            String companyId = rs.getString("company_id");

            addVehicleRequest(fullName, plate, vehiclePrice, companyId);
        }
        
        // Push items to the top
        jPanel2.add(javax.swing.Box.createVerticalGlue());

    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }

    jPanel2.revalidate();
    jPanel2.repaint();
}
    // ADD THIS MISSING METHOD
    private void addVehicleRequest(String name, String plate, String price, String companyId) {
     
    // 1. Instantiate your custom 'request' panel
    view.request row = new view.request();
    
    // 2. Set the data inside the row
    row.setShortData(name, plate, price, companyId);
    
    // 3. IMPORTANT: Pass 'this' so the row knows which frame to refresh later
    row.setMainFrame(this);
    
    // 4. Add the row to your container (jPanel2)
    jPanel2.add(row);
}
    
    
 private void addSearchEvents() {
    jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
        public void keyReleased(java.awt.event.KeyEvent evt) {
            String query = jTextField1.getText().trim();
            // Ensure this matches the text set in Design/initComponents
            if (query.equals("search vehicles") || query.isEmpty()) {
                loadRequests("");
            } else {
                loadRequests(query);
            }
        }
    });

    jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
        @Override
        public void focusGained(java.awt.event.FocusEvent evt) {
            if (jTextField1.getText().equals("search vehicles")) {
                jTextField1.setText("");
            }
        }
        @Override
        public void focusLost(java.awt.event.FocusEvent evt) {
            if (jTextField1.getText().isEmpty()) {
                jTextField1.setText("search vehicles");
            }
        }
    });

    }
  


    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(920, 650));
        setMinimumSize(new java.awt.Dimension(920, 650));
        setPreferredSize(new java.awt.Dimension(920, 650));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setText("search vehicles");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Search Vehicle Request :");

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout());
        jScrollPane1.setViewportView(jPanel2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new VehicleRequest().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}