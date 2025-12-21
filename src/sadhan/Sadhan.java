/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sadhan;

import controller.Login_Controller;
import view.login;

/**
 *
 * @author hp
 */
public class Sadhan {

    /**
     * @param args the command line arguments
     */
public static void main(String[] args) {
        // TODO code application logic here

       login lg= new login();
       Login_Controller controller = new Login_Controller(lg);
       
       

    }
    
}
