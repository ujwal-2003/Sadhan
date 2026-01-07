/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import UserController.UserDashboardController;
import model.UserDashboardModel;
import dao.UserDashboardDao;
import java.awt.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author hp
 */
public class UserDashboard extends javax.swing.JFrame {
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserDashboard.class.getName());

    /**
     * Creates new form UserDashboard
     */
    private int loggedInUserId;
    private UserDashboardController controller;
    private javax.swing.Timer searchTimer;// Declare this as a class variable at the top
    
    public UserDashboard(int userId) {
        this.loggedInUserId = userId;
        initComponents();
        
        // Initialize the Controller
        this.controller = new UserDashboardController(this);
        
        configureDashboardLayout();
        setupSearchListeners();
        
        // Let the controller handle the data loading
        controller.processWelcomeMessage(loggedInUserId);
        controller.fetchVehicles(""); 

        this.setTitle("Vehicle Rental System - User Dashboard");
        this.setLocationRelativeTo(null);
    }
       public void updateWelcomeLabel(String text) {
        jLabel3.setText(text);
    }

    public void renderVehicleGrid(List<UserDashboardModel> vehicles) {
        items_container.removeAll();
        items_container.setLayout(new GridLayout(0, 5, 20, 20));

        for (UserDashboardModel v : vehicles) {
            vehicleitems item = new vehicleitems();
            
            // Map Model data to the UI Component
            item.setLoggedInUserId(this.loggedInUserId);
            item.setMainFrame(this);
            item.setVehicleDbId(v.getId());
            item.setCompanyId(v.getCompanyId());
            item.setVehicleName(v.getFullName());
            item.setPriceValue(v.getPrice());
            item.setAvailability(v.getAvailabilityStatus());

            // Thumbnail Handling
            if (v.getThumbnailBytes() != null) {
                ImageIcon icon = new ImageIcon(v.getThumbnailBytes());
                Image scaled = icon.getImage().getScaledInstance(160, 90, Image.SCALE_SMOOTH);
                item.setCustomIcon(new ImageIcon(scaled));
            }

            // Callback to controller when user clicks "View"
            item.setOnAction(selected -> controller.openVehicleDetails(selected.getVehicleDbId()));

            items_container.add(item);
        }
        refreshUI();
    }

    public void showBookingPopup(UserDashboardModel vehicle, File frontImg, File sideImg) {
        booknow detailsPanel = new booknow();
        detailsPanel.setLoggedInUserId(this.loggedInUserId);
        detailsPanel.setParentFrame(this);
        
        detailsPanel.setVehicleData(
            vehicle.getId(),
            vehicle.getCompanyId(),
            vehicle.getBrand(),
            vehicle.getModel(),
            vehicle.getType(),
            vehicle.getColour(),
            vehicle.getNumberPlate(),
            String.format("Rs. %.2f", vehicle.getPrice()),
            frontImg,
            sideImg
        );

        JFrame popup = new JFrame("Vehicle Specs: " + vehicle.getFullName());
        popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popup.setContentPane(detailsPanel);
        popup.pack();
        popup.setLocationRelativeTo(null);
        popup.setVisible(true);
    }

    // --- Internal UI Logic ---

    private void setupSearchListeners() {
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                if (searchTimer != null && searchTimer.isRunning()) searchTimer.stop();
                
                searchTimer = new javax.swing.Timer(300, e -> {
                    String search = jTextField1.getText().trim();
                    controller.fetchVehicles(search.equals("Search Vehicle") ? "" : search);
                });
                searchTimer.setRepeats(false);
                searchTimer.start();
            }
        });
    }

    private void refreshUI() {
        items_container.revalidate();
        items_container.repaint();
        SwingUtilities.invokeLater(() -> scrollpannel.getVerticalScrollBar().setValue(0));
    }

    public void showEmptyState(String searchText) {
        items_container.removeAll();
        items_container.setLayout(new BorderLayout());
        JLabel empty = new JLabel("No matches found for '" + searchText + "'", SwingConstants.CENTER);
        items_container.add(empty);
        refreshUI();
    }

    public void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void configureDashboardLayout() {
    // 1. Setup the grid for items (5 columns, infinite rows)
    items_container.setLayout(new java.awt.GridLayout(0, 5, 20, 20));
    items_container.setBackground(java.awt.Color.WHITE);

    // 2. Create a wrapper so the grid stays at the top (BorderLayout.NORTH)
    // instead of stretching to fill the whole screen
    JPanel scrollContentWrapper = new JPanel(new java.awt.BorderLayout());
    scrollContentWrapper.setBackground(java.awt.Color.WHITE);
    scrollContentWrapper.add(items_container, java.awt.BorderLayout.NORTH);

    // 3. Link the wrapper to the ScrollPane
    scrollpannel.setViewportView(scrollContentWrapper);
    scrollpannel.getViewport().setBackground(java.awt.Color.WHITE);
    
    // 4. Visual styling
    scrollpannel.setBorder(BorderFactory.createEmptyBorder()); 
    scrollpannel.getVerticalScrollBar().setUnitIncrement(25);
    scrollpannel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
}
           

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        llogo = new javax.swing.JLabel();
        jlogout = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jhistory = new javax.swing.JButton();
        llogout = new javax.swing.JLabel();
        lhistory = new javax.swing.JLabel();
        jprofile = new javax.swing.JButton();
        scrollpannel = new javax.swing.JScrollPane();
        items_container = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        llogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        getContentPane().add(llogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, 110));

        jlogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout_box_icon_206016 (1).png"))); // NOI18N
        jlogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jlogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jlogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 20, 37, -1));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setText("Search Vehicle");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 900, 30));

        jhistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/history-clock-button_icon-icons.com_72701 (2).png"))); // NOI18N
        jhistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jhistoryActionPerformed(evt);
            }
        });
        getContentPane().add(jhistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 37, -1));

        llogout.setForeground(new java.awt.Color(255, 255, 255));
        llogout.setText("logout");
        getContentPane().add(llogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 60, 37, -1));

        lhistory.setForeground(new java.awt.Color(255, 255, 255));
        lhistory.setText("history");
        getContentPane().add(lhistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 60, 37, -1));

        jprofile.setText("profile");
        jprofile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jprofileMouseClicked(evt);
            }
        });
        jprofile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jprofileActionPerformed(evt);
            }
        });
        getContentPane().add(jprofile, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 87, 65));

        scrollpannel.setBackground(new java.awt.Color(255, 255, 255));

        items_container.setBackground(new java.awt.Color(255, 255, 255));
        items_container.setLayout(new java.awt.GridLayout(1, 0));
        scrollpannel.setViewportView(items_container);

        getContentPane().add(scrollpannel, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 1100, 620));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh_icon-icons.com_50052 (1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, 30, 30));

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("WELCOME   : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 480, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/userdashboard.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jlogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jlogoutActionPerformed
       int choice = javax.swing.JOptionPane.showConfirmDialog(this, "Logout?", "Confirm", javax.swing.JOptionPane.YES_NO_OPTION);
        if (choice == javax.swing.JOptionPane.YES_OPTION) {
            this.dispose();
            new login().setVisible(true);
        }
    
        
    }//GEN-LAST:event_jlogoutActionPerformed

    private void jprofileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jprofileMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jprofileMouseClicked

    private void jprofileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jprofileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jprofileActionPerformed

    private void jhistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jhistoryActionPerformed
         // 1. Create a new window (JFrame)
    javax.swing.JFrame frame = new javax.swing.JFrame("My Rental History");
    
    // 2. Add your panel into the window (passing the loggedInUserId)
    // Note: ensure you've updated your UserDashboard constructor to receive/store this ID
    UserHistory historyPanel = new UserHistory(this.loggedInUserId); 
    
    frame.add(historyPanel);
    
    // 3. Configure window behavior
    frame.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE); // Close only the popup, not the app
    frame.pack(); // Adjust size to fit the panel
    frame.setLocationRelativeTo(this); // Center it over the dashboard
    frame.setVisible(true); // Show it!
    }//GEN-LAST:event_jhistoryActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         // 1. Reset the search field text
    jTextField1.setText("Search Vehicle");
     controller.fetchVehicles(""); // Matches the method name in the Controller
    }//GEN-LAST:event_jButton2ActionPerformed

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel items_container;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jhistory;
    private javax.swing.JButton jlogout;
    private javax.swing.JButton jprofile;
    private javax.swing.JLabel lhistory;
    private javax.swing.JLabel llogo;
    private javax.swing.JLabel llogout;
    private javax.swing.JScrollPane scrollpannel;
    // End of variables declaration//GEN-END:variables
}
