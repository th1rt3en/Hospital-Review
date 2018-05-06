/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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

    public UserDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

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
    }
}
