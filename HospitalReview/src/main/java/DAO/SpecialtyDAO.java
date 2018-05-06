/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import DBConnection.DatabaseConnection;
import Model.*;
import java.util.ArrayList;

/**
 *
 * @author Negarr
 */
public class SpecialtyDAO {
    
    private final Connection conn;   
    private static final String GENERAL_INSERT_STATEMENT = "INSERT INTO `general-specialty` "
            + "(Name) VALUES (?)";
    private static final String SPECIFIC_INSERT_STATEMENT = "INSERT INTO `specific-specialty` "
            + "(Name) VALUES (?)";
    private static final String ASSOCIATION_INSERT_STATEMENT = "INSERT INTO specialty "
            + "(Specific_Specialty_ID, General_Specialty_ID) VALUES (?, ?)";
    private static final String GENERAL_UPDATE_STATEMENT = "UPDATE `general-specialty` "
            + "SET Name = ? WHERE ID = ?";
    private static final String SPECIFIC_UPDATE_STATEMENT = "UPDATE `specific-specialty` "
            + "SET Name = ? WHERE ID = ?";
    private static final String ASSOCIATION_UPDATE_STATEMENT = "UPDATE specialty "
            + "SET Specific_Specialty_ID = ?, General_Specialty_ID = ?";
    private static final String GENERAL_DELETE_STATEMENT = "DELETE `general-specialty` "
            + "WHERE ID = ?";
    private static final String SPECIFIC_DELETE_STATEMENT = "DELETE `specific-specialty` "
            + "WHERE ID = ?";
    private static final String ASSOCIATION_DELETE_STATEMENT = "DELETE specialty "
            + "WHERE Specific_Specialty_ID = ?, General_Specialty_ID = ?";    
    private static final String GENERAL_SELECT_STATEMENT = "SELECT * FROM `general-specialty`";
    private static final String SPECIFIC_SELECT_STATEMENT = "SELECT * FROM `specific-specialty`";
    private static final String LAST_INSERT_STATEMENT = "SELECT LAST_INSERT_ID()";
    
    public SpecialtyDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
    public int insertGeneralSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(GENERAL_INSERT_STATEMENT);
        ps.setString(1, specialty.getGeneralName());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public int insertSpecificSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(SPECIFIC_INSERT_STATEMENT);
        ps.setString(1, specialty.getSpecificName());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public void insertAssociation(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(ASSOCIATION_INSERT_STATEMENT);
        ps.setInt(1, specialty.getSpecificId());
        ps.setInt(2, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public void updateGeneralSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException  {
        PreparedStatement ps = conn.prepareStatement(GENERAL_UPDATE_STATEMENT);
        ps.setString(1, specialty.getGeneralName());
        ps.setInt(2, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public void updateSpecificSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException  {
        PreparedStatement ps = conn.prepareStatement(SPECIFIC_UPDATE_STATEMENT);
        ps.setString(1, specialty.getGeneralName());
        ps.setInt(2, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public void updateAssociation(Specialty specialty) throws SQLException, ClassNotFoundException  {
        PreparedStatement ps = conn.prepareStatement(ASSOCIATION_UPDATE_STATEMENT);
        ps.setInt(1, specialty.getSpecificId());
        ps.setInt(2, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public void deleteGeneralSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(GENERAL_DELETE_STATEMENT);
        ps.setInt(1, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public void deleteSpecificSpecialty(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(SPECIFIC_DELETE_STATEMENT);
        ps.setInt(1, specialty.getSpecificId());
        ps.executeUpdate();
    }
    
    public void deleteAssociation(Specialty specialty) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(ASSOCIATION_DELETE_STATEMENT);
        ps.setInt(1, specialty.getSpecificId());
        ps.setInt(2, specialty.getGeneralId());
        ps.executeUpdate();
    }
    
    public ArrayList<Specialty> getAllGeneralSpecialties() throws SQLException, ClassNotFoundException {
        ArrayList<Specialty> specialties = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(GENERAL_SELECT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Specialty specialty = new Specialty();
            specialty.setGeneralId(rs.getInt(1));
            specialty.setGeneralName(rs.getString(2));
            specialties.add(specialty);
        }
        return specialties;
    }
    
    public ArrayList<Specialty> getAllSpecificSpecialties() throws SQLException, ClassNotFoundException {
        ArrayList<Specialty> specialties = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(SPECIFIC_SELECT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Specialty specialty = new Specialty();
            specialty.setSpecificId(rs.getInt(1));
            specialty.setSpecificName(rs.getString(2));
            specialties.add(specialty);
        }
        return specialties;
    }
    
}
