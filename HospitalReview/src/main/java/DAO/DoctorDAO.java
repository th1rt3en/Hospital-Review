/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import DBConnection.DatabaseConnection;
import Model.Doctor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Negarr
 */
public class DoctorDAO {
    private Connection conn;
    private final String GetAllDoctors = "SELECT * FROM Doctor";
    private final String InsertDoctor = "INSERT INTO Doctor VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String UpdateDoctor = "UPDATE Doctor SET First_Name = ?, Last_Name = ?, Gender = ?, Degree = ?, Accepted_Insurance = ?, Office_Hours = ?, Languages = ?, Hospital_ID = ? WHERE ID = ?";
    private final String SearchDoctorByProperties = "SELECT * FROM Doctor Where 1 = 1";
    
    public DoctorDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
    public List<Doctor> getAllDoctors() throws SQLException {
        PreparedStatement ps = conn.prepareStatement(GetAllDoctors);
        ResultSet rs = ps.executeQuery();
        List<Doctor> doctorList = new ArrayList<>();
        while (rs.next()) {
            Doctor temp = new Doctor();
            temp.setId(rs.getInt("ID"));
            temp.setFirstName(rs.getString("First_Name"));
            temp.setLastName(rs.getString("Last_Name"));
            temp.setGender(rs.getString("Gender"));
            temp.setDegree(rs.getString("Degree"));
            temp.setAcceptedInsurance(rs.getBoolean("Accepted_Insurance"));
            temp.setOfficeHours(rs.getString("Office_Hours"));
            temp.setLanguages(rs.getString("Languages"));
            temp.setHospitalId(rs.getInt("Hospital_ID"));
            doctorList.add(temp);
        }
        return doctorList;
    }
    
    public void insertDoctor(Doctor doctor) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(InsertDoctor, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, doctor.getFirstName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getGender());
        ps.setString(4, doctor.getDegree());
        ps.setBoolean(5, doctor.isAcceptedInsurance());
        ps.setString(6, doctor.getOfficeHours());
        ps.setString(7, doctor.getLanguages());
        ps.setInt(8, doctor.getHospitalId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            doctor.setId(rs.getInt(1));
        }
    }
    
    public void updateDoctor(Doctor doctor) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(UpdateDoctor);
        ps.setString(1, doctor.getFirstName());
        ps.setString(2, doctor.getLastName());
        ps.setString(3, doctor.getGender());
        ps.setString(4, doctor.getDegree());
        ps.setBoolean(5, doctor.isAcceptedInsurance());
        ps.setString(6, doctor.getOfficeHours());
        ps.setString(7, doctor.getLanguages());
        ps.setInt(8, doctor.getHospitalId());
        ps.setInt(9, doctor.getId());
        ps.executeQuery();
    }
    
    public List<Doctor> searchDoctorByProperties(Doctor doctor) throws SQLException {
        String query = SearchDoctorByProperties;
        if (doctor.getFirstName() != null) {
            query += " First_Name = " + doctor.getFirstName();
        }
        if (doctor.getLastName() != null) {
            query += " Last_Name = " + doctor.getLastName();
        }
        if (doctor.getGender() != null) {
            query += " Gender = " + doctor.getGender();
        }
        if (doctor.getDegree() != null) {
            query += " Degree = " + doctor.getDegree();
        }
        if (doctor.isAcceptedInsurance() != null) {
            query += " Accepted_Insurance = " + doctor.isAcceptedInsurance();
        }
        //if (doctor.getOfficeHours())
        //if (doctor.getLanguages() != null) {
        if (doctor.getHospitalId() != -1) {
            query += " Hospital_ID = " + doctor.getHospitalId();
        }
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Doctor> doctorList = new ArrayList<>();
        while (rs.next()) {
             Doctor temp = new Doctor();
            temp.setId(rs.getInt("ID"));
            temp.setFirstName(rs.getString("First_Name"));
            temp.setLastName(rs.getString("Last_Name"));
            temp.setGender(rs.getString("Gender"));
            temp.setDegree(rs.getString("Degree"));
            temp.setAcceptedInsurance(rs.getBoolean("Accepted_Insurance"));
            temp.setOfficeHours(rs.getString("Office_Hours"));
            temp.setLanguages(rs.getString("Languages"));
            temp.setHospitalId(rs.getInt("Hospital_ID"));
            doctorList.add(temp);
        }
        return doctorList;
    }
    
    public static void main(String[] args) {
        try {
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = new Doctor("hai", "pham", "male", "bachelor", true, "9-5", "VN-EN", 1);
            dao.insertDoctor(doc);
        } catch (Exception ex) {
            
            System.out.print(ex);
        }
    }
}
