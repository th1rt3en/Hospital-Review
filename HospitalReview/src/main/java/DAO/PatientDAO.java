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
public class PatientDAO {

    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO patient "
            + "(ID, First_Name, Last_Name, Address, Gender, Languages) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_STATEMENT = "UPDATE patient "
            + "SET First_Name = ?, Last_Name = ?, Address = ?, Gender = ?, Languages = ? "
            + "WHERE ID = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM patient WHERE ID = ?";
    private static final String SELECT_STATEMENT = "SELECT u.*, p.First_Name, "
            + "p.Last_Name, p.Address, p.Gender, p.Languages "
            + "FROM patient AS p JOIN `user` AS u";
    
    public PatientDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

    public ArrayList<Patient> getAllPatients() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getInt(1));
            patient.setEmail(rs.getString(2));
            patient.setPassword(Cryptography.decrypt(rs.getString(3)));
            patient.setActivated(rs.getBoolean(4));
            patient.setType(rs.getString(5));
            patient.setFirstName(rs.getString(6));
            patient.setLastName(rs.getString(7));
            patient.setAddress(rs.getString(8));
            patient.setGender(rs.getString(9));
            patient.setLanguages(rs.getString(10));
            patients.add(patient);
        }
        return patients;
    }
    
    public void updatePatient(Patient patient) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
        ps.setString(1, patient.getFirstName());
        ps.setString(2, patient.getLastName());
        ps.setString(3, patient.getAddress());
        ps.setString(4, patient.getGender());
        ps.setString(5, patient.getLanguages());
        ps.setInt(6, patient.getId());
        ps.executeUpdate();
    }
    
    public void deletePatient(Patient patient) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(DELETE_STATEMENT);
        ps.setInt(1, patient.getId());
        ps.executeUpdate();
    }
    
    public void insertPatient(Patient patient) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
        ps.setInt(1, patient.getId());
        ps.setString(2, patient.getFirstName());
        ps.setString(3, patient.getLastName());
        ps.setString(4, patient.getAddress());
        ps.setString(5, patient.getGender());
        ps.setString(6, patient.getLanguages());
        ps.executeUpdate();
    }    
    
}
