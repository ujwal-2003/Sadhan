/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class MySqlConnection implements Database {
    
    /**
     *
     * @return
     */
    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "actofgod12345";
            String database = "Sadhan";
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database,
                    username,
                    password
            );

            if (connection != null) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection unsuccessful");
            }

            return connection;

        } catch (SQLException e) {
            System.out.println("Error while connecting: " + e.getMessage());
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
            System.out.println("Error while closing: " + e.getMessage());
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);

        } catch (SQLException e) {
            System.out.println("Error in runQuery: " + e.getMessage());
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);

        } catch (SQLException e) {
            System.out.println("Error in executeUpdate: " + e.getMessage());
            return -1;
        }
    }
}



