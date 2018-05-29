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
import Tools.RandomString;
import Tools.SHAHash;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Negarr
 */
public class UserDAO implements AutoCloseable {

    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO `user` "
            + "(Email, Password, Is_Activated, Type) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE `user` "
            + "SET Email = ?, Password = ?, Is_Activated = ?, Type = ? WHERE ID = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM `user` WHERE ID = ?";
    private static final String SELECT_BY_EMAIL_STATEMENT = "SELECT * FROM USER WHERE Email = ?";
    private static final String LAST_INSERT_STATEMENT = "SELECT LAST_INSERT_ID()";
    private static final String AUTHORIZATION_STATEMENT = "SELECT * FROM User WHERE Email = ? AND Password = ?";
    private static final String CREATE_RESET_KEY_STATEMENT = "DELETE FROM ResetPassword WHERE User_id = ?;"
            + "INSERT INTO ResetPassword VALUES(?, ?)";
    private static final String CHECK_RESET_KEY_STATEMENT = "SELECT * FROM ResetPassword WHERE User_id = ? Key = ?";

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
            user.setPassword(rs.getString(3));
            user.setActivated(rs.getBoolean(4));
            user.setType(rs.getString(5)); //1 patient, 2 hospital, 3 admin
        }
        return user;
    }
    
    public void updateUser(User user) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
        ps.setString(1, user.getEmail());
        ps.setString(2, SHAHash.hash(user.getPassword()));
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
        ps.setString(2, SHAHash.hash(user.getPassword()));
        ps.setBoolean(3, user.isActivated());
        ps.setString(4, user.getType());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public User authorization(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(AUTHORIZATION_STATEMENT);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setActivated(rs.getBoolean(4));
                user.setType(rs.getString(5)); //1 patient, 2 hospital, 3 admin
                if (user.isActivated()) {
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User authorization(HttpSession session, Cookie[] cookies) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email")) {
                    user.setEmail(cookie.getValue());
                }
                else if (cookie.getName().equals("password")) {
                    user.setPassword(cookie.getValue());
                }
                if (user.getEmail() != null && user.getPassword() != null) {
                    break;
                }
            }
        }
        if (user.getEmail() == null || user.getPassword() == null) {
            return null;
        }
        try {
            PreparedStatement ps = conn.prepareStatement(AUTHORIZATION_STATEMENT);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setActivated(rs.getBoolean(4));
                user.setType(rs.getString(5)); //1 patient, 2 hospital, 3 admin
                if (user.isActivated()) {
                    return user;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void createResetKey(User user) {
        try {
            RandomString rand = new RandomString(256); 
            String key = rand.nextString();
            PreparedStatement ps = conn.prepareStatement(CREATE_RESET_KEY_STATEMENT);
            ps.setInt(1, user.getId());
            ps.setInt(2, user.getId());
            ps.setString(3, key);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkResetKey(User user, String key) {
        try {
            PreparedStatement ps = conn.prepareStatement(CHECK_RESET_KEY_STATEMENT);
            ps.setInt(1, user.getId());
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void setAutoCommit(boolean value) throws SQLException {
        this.conn.setAutoCommit(value);
    }
    
    public void rollback() {
        try {
            this.conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void commit() throws SQLException {
        this.conn.commit();
    }
    
    @Override
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
