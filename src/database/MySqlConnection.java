/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class MySqlConnection implements Database {
    
    /**
     *
     * @return
     */
    private static MySqlConnection instance;
    private Connection connection;
    
    private MySqlConnection() {
        // This ensures nobody can use 'new MySqlConnection()' outside this class
    }
    // 1. ADD THIS: The static getInstance method
    public static MySqlConnection getInstance() {
        if (instance == null) {
            instance = new MySqlConnection();
        }
        return instance;
    }

    // 2. ADD THIS: A method to get or open the connection
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = openConnection();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connection;
    }
    @Override
    
    public Connection openConnection() {
        try {
          
            String username = "root";
            String password = "actofgod12345";
            String database = "Sadhan";
            // Load Driver (Optional in modern JDBC but safer to include)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    username,
                    password
            );
           System.out.println("Connection successful to: " + "sadhan");
            return conn;
            

       } catch (Exception e) {
            System.out.println("Failed to open connection: " + e.getMessage());
            return null;
        }
            
 }


    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        }
    }
}



