/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author hp
 */
public class admin_userinfoitems extends javax.swing.JPanel {

    /**
     * Creates new form seekerinfo
     */
    public admin_userinfoitems() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, java.awt.Color.BLACK));
    }
      public void setShortData(String username, String email) {      
    UserName.setText("User Name : "+username);    
    Email.setText("Email : "+email);
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UserName = new javax.swing.JLabel();
        Email = new javax.swing.JLabel();
        view = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        UserName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        UserName.setText("User Name");

        Email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Email.setText("email");

        view.setBackground(new java.awt.Color(51, 255, 51));
        view.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        view.setText("View");
        view.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 253, Short.MAX_VALUE)
                .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserName, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(view)
                    .addComponent(Email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Email;
    private javax.swing.JLabel UserName;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
