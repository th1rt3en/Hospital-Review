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
import Tools.Cryptography;

/**
 *
 * @author Negarr
 */
public class HospitalDAO {
    
    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO hospital "
            + "(ID, Name, Address, Website) VALUES (?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE hospital "
            + "SET Name = ?, Address = ?, Website = ? WHERE ID = ?";
    private static final String DETELE_STATEMENT = "DELETE FROM hospital WHERE ID = ?";
    private static final String SELECT_STATEMENT = "SELECT u.*, h.Name, h.Address, h.Website"
            + "FROM hospital AS h JOIN `user` AS u";

    
    public HospitalDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
    public ArrayList<Hospital> getAllHospitals() throws SQLException, ClassNotFoundException {
        ArrayList<Hospital> hospitals = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Hospital hospital = new Hospital();
            hospital.setId(rs.getInt(1));
            hospital.setEmail(rs.getString(2));
            hospital.setPassword(Cryptography.decrypt(rs.getString(3)));
            hospital.setActivated(rs.getBoolean(4));
            hospital.setType(rs.getString(5));
            hospital.setName(rs.getString(6));
            hospital.setAddress(rs.getString(7));
            hospital.setWebsite(rs.getString(8));
            hospitals.add(hospital);
        }
        return hospitals;
    }
    
    public void updateHospital(Hospital hospital) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
        ps.setString(1, hospital.getName());
        ps.setString(2, hospital.getAddress());
        ps.setString(3, hospital.getWebsite());
        ps.setInt(4, hospital.getId());
        ps.executeUpdate();
    }
    
    public void deleteHospital(Hospital hospital) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(DETELE_STATEMENT);
        ps.setInt(1, hospital.getId());
        ps.executeUpdate();
    }
    
    public void insertHospital(Hospital hospital) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
        ps.setInt(1, hospital.getId());
        ps.setString(2, hospital.getName());
        ps.setString(3, hospital.getAddress());
        ps.setString(4, hospital.getWebsite());
        ps.executeUpdate();
    }
    
}
