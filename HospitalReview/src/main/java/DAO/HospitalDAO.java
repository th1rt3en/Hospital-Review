/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hai06
 */
public class HospitalDAO {
    private Connection conn;
    private final String getAllHospital = "SELECT * FROM Hospital";
    private final String getAllHospitalActivated = "SELECT Hospital.ID as ID, Name, Hospital_Address, Website JOIN Hospital, User ON Hospital.ID = User.ID WHERE Is_Activated = 1";
    private final String insertHospital = "INSERT INTO Hospital VALUES(?, ?, ?, ?)";
    private final String updateHospital = "UPDATE Hospital SET Name = ?, Hospital_Address = ?, Website = ? WHERE ID = ?";
    private final String deleteHospital = "DELETE Hospital WHERE ID = ?";
    
    public List<Hospital> getAllHospital() throws SQLException {
        PreparedStatement ps = conn.prepareCall(getAllHospital);
        ResultSet rs = ps.executeQuery();
        List<Hospital> listHospital = new ArrayList<>();
        while(rs.next()) {
            Hospital hospital = new Hospital();
            hospital.setId(rs.getInt("ID"));
            hospital.setName(rs.getString("Name"));
            hospital.setHospitalAddress(rs.getString("Hospital_Address"));
            hospital.setWebsite(rs.getString("Website"));
            
            listHospital.add(hospital);
        }
        
        return listHospital;
    }
    
    public List<Hospital> getAllHospitalActivated() throws SQLException {
        PreparedStatement ps = conn.prepareCall(getAllHospitalActivated);
        ResultSet rs = ps.executeQuery();
        List<Hospital> listHospital = new ArrayList<>();
        while(rs.next()) {
            Hospital hospital = new Hospital();
            hospital.setId(rs.getInt("ID"));
            hospital.setName(rs.getString("Name"));
            hospital.setHospitalAddress(rs.getString("Hospital_Address"));
            hospital.setWebsite(rs.getString("Website"));
            
            listHospital.add(hospital);
        }
        
        return listHospital;
    }
    
    public void insertHospital(Hospital hospital) throws SQLException {
        PreparedStatement ps = conn.prepareCall(insertHospital);
        ps.setInt(1, hospital.getId());
        ps.setString(2, hospital.getName());
        ps.setString(3, hospital.getHospitalAddress());
        ps.setString(4, hospital.getWebsite());
        ResultSet rs = ps.executeQuery();
    }
    
    public void updateHospital(Hospital hospital) throws SQLException {
        PreparedStatement ps = conn.prepareCall(updateHospital);
        ps.setString(1, hospital.getName());
        ps.setString(2, hospital.getHospitalAddress());
        ps.setString(3, hospital.getWebsite());
        ps.setInt(4, hospital.getId());
        ps.execute();
    }
    
}