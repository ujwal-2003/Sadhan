/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserController;


import javax.swing.JOptionPane;
import java.io.File;
import java.nio.file.Files;
import model.UserData;
import view.requestVehicleApproval;
import dao.UserDao;




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
            UserData user = new UserData();

            // 1️⃣ Get text data
            user.setCompanyId(1);
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

            // 3️⃣ Save and check result
            boolean success = userDao.requestVehicleApproval(user);

            if (success) {
                JOptionPane.showMessageDialog(view, "Vehicle request sent successfully!");
                view.dispose();
            } 

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage());
        }
    }
  
    
}
