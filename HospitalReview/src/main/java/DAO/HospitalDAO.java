/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

<<<<<<< HEAD
import DBConnection.DatabaseConnection;
import Model.Hospital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

=======
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

    
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
    public HospitalDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
<<<<<<< HEAD
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
        PreparedStatement ps = conn.prepareStatement(insertHospital, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, hospital.getId());
        ps.setString(2, hospital.getName());
        ps.setString(3, hospital.getHospitalAddress());
        ps.setString(4, hospital.getWebsite());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            hospital.setId(rs.getInt(1));
        }
    }
    
    public void updateHospital(Hospital hospital) throws SQLException {
        PreparedStatement ps = conn.prepareCall(updateHospital);
        ps.setString(1, hospital.getName());
        ps.setString(2, hospital.getHospitalAddress());
        ps.setString(3, hospital.getWebsite());
        ps.setInt(4, hospital.getId());
        ps.execute();
    }
    
    public static void main(String args[]) {
        try {
            HospitalDAO dao = new HospitalDAO();
            Hospital hos = new Hospital(1, "bv", "123", "www.net");
            dao.insertHospital(hos);
            
        } catch (SQLException ex) {
            Logger.getLogger(HospitalDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HospitalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
=======
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
    
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
}
