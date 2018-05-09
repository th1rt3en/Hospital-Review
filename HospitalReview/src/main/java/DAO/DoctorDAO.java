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
public class DoctorDAO {
<<<<<<< HEAD
    private Connection conn;
    private final String GetAllDoctors = "SELECT * FROM Doctor";
    private final String InsertDoctor = "INSERT INTO Doctor VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UpdateDoctor = "UPDATE Doctor SET First_Name = ?, Last_Name = ?, Gender = ?, Degree = ?, Accepted_Insurance = ?, Office_Hours = ?, Languages = ?, Hospital_ID = ? WHERE ID = ?";
    private final String SearchDoctorByProperties = "SELECT * FROM Doctor Where 1 = 1";
    
=======

    private final Connection conn;
    private static final String INSERT_STATEMENT = "INSERT INTO doctor "
            + "(First_Name, Last_Name, Gender, Degree, Accepted_Insurance, Office_Hours, Languages, Hospital_ID) "
            + "VALUES (?,?,?,?,?,?,?,?)";    
    private static final String SELECT_STATEMENT = "SELECT * FROM doctor";
    private static final String DELETE_STATEMENT = "DELETE FROM doctor WHERE ID = ?";
    private static final String UPDATE_STATEMENT = "UPDATE doctor "
            + "SET First_Name = ?, Last_Name = ?, Gender = ?, Degree = ?, "
            + "Accepted_Insurance = ?, Office_Hours = ?, Languages = ?, Hospital_ID = ?"
            + "WHERE ID = ?"; 
    private static final String SEARCH_STATEMENT = "SELECT * FROM doctor "
            + "WHERE First_Name LIKE ?, Last_Name LIKE ?, Gender LIKE ?, Degree LIKE ?";
    private static final String LAST_INSERT_STATEMENT = "SELECT LAST_INSERT_ID()";

>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    public DoctorDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }

    public ArrayList<Doctor> searchDoctors(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(SEARCH_STATEMENT);
        ps.setString(1, doctor.getFirstName() == null ? "%%" : "%" + doctor.getFirstName() + "%");
        ps.setString(2, doctor.getLastName() == null ? "%%" : "%" + doctor.getLastName() + "%");
        ps.setString(3, doctor.getGender() == null ? "%%" : "%" + doctor.getGender() + "%");
        ps.setString(4, doctor.getDegree() == null ? "%%" : "%" + doctor.getDegree() + "%");
        ResultSet rs = ps.executeQuery();
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (rs.next()) {
            Doctor d = new Doctor();
            d.setId(rs.getInt(1));
            d.setFirstName(rs.getString(2));
            d.setLastName(rs.getString(3));
            d.setGender(rs.getString(4));
            d.setDegree(rs.getString(5));
            d.setAcceptedInsurance(rs.getBoolean(6));
            d.setOfficeHours(rs.getString(7));
            d.setLanguages(rs.getString(8));
            d.setHospitalId(rs.getInt(9));
            doctors.add(d);
        }
        return doctors;
    }
    
<<<<<<< HEAD
    public void insertDoctor(Doctor doctor) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(InsertDoctor, PreparedStatement.RETURN_GENERATED_KEYS);
=======
    public ArrayList<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(SELECT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        ArrayList<Doctor> doctors = new ArrayList<>();
        while (rs.next()) {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt(1));
            doctor.setFirstName(rs.getString(2));
            doctor.setLastName(rs.getString(3));
            doctor.setGender(rs.getString(4));
            doctor.setDegree(rs.getString(5));
            doctor.setAcceptedInsurance(rs.getBoolean(6));
            doctor.setOfficeHours(rs.getString(7));
            doctor.setLanguages(rs.getString(8));
            doctor.setHospitalId(rs.getInt(9));
            doctors.add(doctor);
        }
        return doctors;
    }

    public void updateDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_STATEMENT);
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
        ps.setString(1, doctor.getFirstName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getGender());
        ps.setString(4, doctor.getDegree());
        ps.setBoolean(5, doctor.isAcceptedInsurance());
        ps.setString(6, doctor.getOfficeHours());
        ps.setString(7, doctor.getLanguages());
        ps.setInt(8, doctor.getHospitalId());
<<<<<<< HEAD
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            doctor.setId(rs.getInt(1));
        }
=======
        ps.setInt(9, doctor.getId());
        ps.executeUpdate();
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    }
    
    public void deleteDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(DELETE_STATEMENT);
        ps.setInt(1, doctor.getId());
        ps.executeUpdate();
    }
    
    public int insertDoctor(Doctor doctor) throws SQLException, ClassNotFoundException {
        PreparedStatement ps = conn.prepareStatement(INSERT_STATEMENT);
        ps.setString(1, doctor.getFirstName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getGender());
        ps.setString(4, doctor.getDegree());
        ps.setBoolean(5, doctor.isAcceptedInsurance());
        ps.setString(6, doctor.getOfficeHours());
        ps.setString(7, doctor.getLanguages());
        ps.setInt(8, doctor.getHospitalId());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
<<<<<<< HEAD
    public static void main(String[] args) {
        try {
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = new Doctor("hai", "pham", "male", "bachelor", true, "9-5", "VN-EN", 1);
            dao.insertDoctor(doc);
        } catch (Exception ex) {
            
            System.out.print(ex);
        }
    }
=======
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
}
