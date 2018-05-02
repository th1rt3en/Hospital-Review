/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnection.DatabaseConnection;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hai06
 */
public class UserDAO {

    private Connection conn;
    private final String authorization = "SELECT Email, Password, Type From User WHERE Email = ? AND Password = ? AND Is_Activated = TRUE";
    private final String insertUser = "INSERT INTO User VALUES(null, ?, ?, ?, ?)";
    private final String updateUser = "";
    private final String deleteUser = "";
    
    public UserDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

    public int authorization(User user) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(authorization);
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
        PreparedStatement ps = conn.prepareStatement(insertUser, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, user.getEmail());
        ps.setString(2, user.getPassword());
        ps.setBoolean(3, user.isActivated());
        ps.setInt(4, user.getType());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            user.setId(rs.getInt(1));
        }
    }

    public static void main(String[] args) {
        try {
            UserDAO u = new UserDAO();
            User user = new User("hai06780@gmail.com", "1107", true, 3);
            u.insertUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateUser(User user) {

    }

    public void deleteUser(User user) {

    }
}
