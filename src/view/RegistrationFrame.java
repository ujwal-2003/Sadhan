/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RegistrationFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public RegistrationFrame() {

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new Company_Registration_Panel(), "company");
        mainPanel.add(new User_Registration_Panel(), "seeker");

        add(mainPanel);

        setTitle("Registration");
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void showCard(String name) {
    java.awt.CardLayout cl = (java.awt.CardLayout) mainPanel.getLayout(); 
    cl.show(mainPanel, name);
    mainPanel.revalidate();
    mainPanel.repaint();
   }
  
 

}
