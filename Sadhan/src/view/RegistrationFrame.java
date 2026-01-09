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
        setSize(1370, 770);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showCard(String name) {
        cardLayout.show(mainPanel, name);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistrationFrame();
            }
        });
    }
}
