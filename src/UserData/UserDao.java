/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserData;

import Model.UserModel;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Aavash
 */
public class UserDao {
    MySqlConnection mysql = new MySqlConnection();
    
    public void signup(UserModel user){
        Connection conn = mysql.openConnection();
        String sql = "insert into user (username, eail, password) values(?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.executeUpdate();
            
        } catch(Exception ex){
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
    }
    public boolean check(UserModel user){
        Connection conn = mysql.openConnection();
        String sql = "select * from user where email = ? or username = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getEmail());
            ResultSet result = pstm.executeQuery();
            return result.next();
        } catch(Exception ex){
            System.out.println(ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }
}
