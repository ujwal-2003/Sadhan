/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.requestVehicleApproval;
import java.io.File;
import java.nio.file.Files;
import model.UserData;




/**
 *
 * @author hp
 */
public class userController {
    private requestVehicleApproval view;
    private UserDao userDao;

    public userController(requestVehicleApproval view) {
        this.view = view;
        this.userDao = new UserDao();

        // 🔥 Link button click to controller
        this.view.addUserListener(e -> requestVehicle());
    }

    public void open() {
        view.setVisible(true);
    }

    private void requestVehicle() {
        try {
            UserData user = new UserData() ;

            // 1️⃣ Get text data
            user.setBrand(view.getBrand().getText());
            user.setModel(view.getModel().getText());
            user.setVehicletype(view.getVehicletype().getText());
            user.setColour(view.getColour().getText());
            user.setNumberPlate(view.getNumberPlate().getText());
            user.setPrice(view.getPrice().getText());

            // 2️⃣ Get images
            File front = view.getFrontImageFile();
            File side = view.getSideImageFile();

            if (front == null || side == null) {
                JOptionPane.showMessageDialog(view, "Please select both images");
                return;
            }

            user.setFrontImage(Files.readAllBytes(front.toPath()));
            user.setSideImage(Files.readAllBytes(side.toPath()));

            // 3️⃣ Save as REQUEST (PENDING)
            userDao.requestVehicleApproval(user);

            JOptionPane.showMessageDialog(view,
                    "Vehicle request sent to admin for approval");

            view.dispose(); // optional: close window

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view,
                    "Error while sending request");
        }
    }
  
    
}
