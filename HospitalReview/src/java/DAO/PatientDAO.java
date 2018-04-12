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
import java.util.List;

/**
 *
 * @author Negarr
 */
public class PatientDAO {

    private Connection conn;

    public PatientDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

    public Patient getPatientByEmail(String email) throws SQLException, ClassNotFoundException {
        Patient p = new Patient();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient WHERE Email = ?");
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            p.setAddress(rs.getString("Address"));
            p.setEmail(rs.getString("Email"));
            p.setFirstName(rs.getString("First_Name"));
            p.setGender(rs.getString("Gender"));
            p.setId(rs.getInt("ID"));
            p.setLanguages(rs.getString("Languages"));
            p.setLastName(rs.getString("Last_Name"));
            p.setPassword(rs.getString("Password"));
        }
        return p;
    }

    public List<Patient> getAllPatients() throws SQLException, ClassNotFoundException {
        List<Patient> patients = new ArrayList<Patient>();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM patient");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Patient p = new Patient();
            p.setAddress(rs.getString("Address"));
            p.setEmail(rs.getString("Email"));
            p.setFirstName(rs.getString("First_Name"));
            p.setGender(rs.getString("Gender"));
            p.setId(rs.getInt("ID"));
            p.setLanguages(rs.getString("Languages"));
            p.setLastName(rs.getString("Last_Name"));
            p.setPassword(rs.getString("Password"));
            patients.add(p);
        }
        return patients;
    }

    public int addPatient(Patient p) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO patient "
                + "(First_Name, Last_Name, Gender, Email, Password, Address, Languages) VALUES (?,?,?,?,?,?,?)");
        ps.setString(1, p.getFirstName());
        ps.setString(2, p.getLastName());
        ps.setString(3, p.getGender());
        ps.setString(4, p.getEmail());
        ps.setString(5, p.getPassword());
        ps.setString(6, p.getAddress());
        ps.setString(7, p.getLanguages());
        ps.executeUpdate();

        Patient patient = getPatientByEmail(p.getEmail());
        return patient.getId();
    }
    
    public void updatePassword(Patient p, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement("UPDATE patient SET Password = ? WHERE ID = ?");
        ps.setString(1, password);
        ps.setInt(2, p.getId());
        ps.executeUpdate();
    }
    
}
