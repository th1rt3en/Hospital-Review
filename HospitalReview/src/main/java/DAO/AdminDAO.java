/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import java.sql.*;
import DBConnection.DatabaseConnection;
import java.util.ArrayList;

/**
 *
 * @author Negarr
 */
public class AdminDAO {
    
    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO admin "
            + "(ID, First_Name, Last_Name) VALUES (?,?,?)";
    private static final String DELETE_STATEMENT = "DELETE FROM admin WHERE ID = ?";
    private static final String UPDATE_STATEMENT = "UPDATE admin "
            + "SET First_Name = ?, Last_Name = ? WHERE ID = ?";
    public AdminDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }   
    
    public void updateAdmin(Admin admin) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
        ps.setString(1, admin.getFirstName());
        ps.setString(2, admin.getLastName());
        ps.setInt(3, admin.getId());
        ps.executeUpdate();
    }
    
    public void deleteAdmin(Admin admin) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(DELETE_STATEMENT);
        ps.setInt(1, admin.getId());
        ps.executeUpdate();
    }
    
    public void insertAdmin(Admin admin) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
        ps.setInt(1, admin.getId());
        ps.setString(2, admin.getFirstName());
        ps.setString(3, admin.getLastName());
        ps.executeUpdate();
    }
    
}
