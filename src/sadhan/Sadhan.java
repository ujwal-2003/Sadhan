/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sadhan;




import database.Database;

import database.MySqlConnection;



/**

 *

 * @author User

 */

public class Sadhan {



    /**

     * @param args the command line arguments

     */

    public static void main(String[] args) {

        // TODO code application logic here

       Database db = new MySqlConnection();

       if(db.openConnection() !=null){

           System.out.println("Connectioon succesful");

         

       }else{

           System.out.println("Not successful");

       }
    }
}


