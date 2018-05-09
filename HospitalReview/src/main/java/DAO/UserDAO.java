/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

<<<<<<< HEAD
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
    
=======
import java.sql.*;
import DBConnection.DatabaseConnection;
import Model.*;
import Tools.Cryptography;

/**
 *
 * @author Negarr
 */
public class UserDAO {

    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO `user` "
            + "(Email, Password, Is_Activated, Type) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE `user` "
            + "SET Email = ?, Password = ?, Is_Activated = ?, Type = ? WHERE ID = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM `user` WHERE ID = ?";
    private static final String SELECT_BY_EMAIL_STATEMENT = "SELECT * FROM USER WHERE Email = ?";
    private static final String LAST_INSERT_STATEMENT = "SELECT LAST_INSERT_ID()";

>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    public UserDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

<<<<<<< HEAD
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

=======
    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        User user = new User();
        PreparedStatement ps = conn.prepareStatement(SELECT_BY_EMAIL_STATEMENT);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            user.setId(rs.getInt(1));
            user.setEmail(rs.getString(2));
            user.setPassword(Cryptography.decrypt(rs.getString(3)));
            user.setActivated(rs.getBoolean(4));
            user.setType(rs.getString(5));
        }
        return user;
    }
    
    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
        ps.setString(1, user.getEmail());
        ps.setString(2, Cryptography.encrypt(user.getPassword()));
        ps.setBoolean(3, user.isActivated());
        ps.setString(4, user.getType());
        ps.setInt(5, user.getId());
        ps.executeUpdate();
    }

    public void deleteUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(DELETE_STATEMENT);
        ps.setInt(1, user.getId());
        ps.executeUpdate();
    }

    public int insertUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
        ps.setString(1, user.getEmail());
        ps.setString(2, Cryptography.encrypt(user.getPassword()));
        ps.setBoolean(3, user.isActivated());
        ps.setString(4, user.getType());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    }
}
