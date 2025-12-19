/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author Aavash
 */

    import java.sql.*;


/**

 *

 * @author User

 */

public interface Database {

    Connection openConnection();

    void closeConnection(Connection conn);

    ResultSet runQuery(Connection conn, String query);

    int excecuteUpdate(Connection conn, String query);

    

}

