/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sadhan;

import database.Database;
import database.MySqlConnection;
import view.login;

public class Sadhan {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(Sadhan.class.getName());

    public static void main(String[] args) {

        // Set Nimbus Look & Feel BEFORE creating UI
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info :
                    javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Start UI
        java.awt.EventQueue.invokeLater(() -> {
            new login().setVisible(true);
        });

        // Test DB connection (optional but OK)
        Database db = new MySqlConnection();
        if (db.openConnection() != null) {
            System.out.println("Connection successful");
        } else {
            System.out.println("Connection failed");
        }
    }
}
