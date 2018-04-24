/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hai06
 */
public class UserDAO {
    private Connection conn;
    private final String authorization = "SELECT Email, Password, Type From User WHERE Email = ? AND Password = ? AND Is_Activated = TRUE";
    private final String insertUser = "INSERT INTO User VALUES(?, ?, ?, ?)";
    private final String updateUser = "";
    private final String deleteUser = "";
    
    public int authorization(User user) throws SQLException {
        PreparedStatement ps = conn.prepareCall(authorization);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getString("Email").equals(user.getEmail()) && rs.getString("Password").equals(user.getPassword())) {
                return rs.getInt("Type");
            }
        }
        return -1;
    }
    
    public void insertUser(User user) throws SQLException {
        PreparedStatement ps = conn.prepareCall(authorization);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setBoolean(3, user.isActivated());
        ps.setInt(4, user.getType());
        ResultSet rs = ps.executeQuery();
        user.setId(rs.getInt("ID"));
    }
    
    public void updateUser(User user) {
        
    }
    
    public void deleteUser(User user) {
        
    }
}
