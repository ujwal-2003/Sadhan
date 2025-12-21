/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import UserData.UserDao;
import UserData.UserData;
import view.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Login_Controller {

    private final login view;
    private final UserDao userDao;

    public Login_Controller(login view) {
        this.view = view;
        this.userDao = new UserDao();

        this.view.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = view.getUsernameField().getText();
            String password = new String(view.getPasswordField().getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Fill all fields");
                return;
            }

            UserData user = new UserData(username, password);
            boolean success = userDao.login(user);

            if (success) {
                JOptionPane.showMessageDialog(view, "Login Successful!");
                // open dashboard here
            } else {
                JOptionPane.showMessageDialog(view, "Invalid username or password");
            }
        }
    }
}
