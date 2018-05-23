/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import DBConnection.DatabaseConnection;
import Model.doc_bookmark;
import java.util.ArrayList;
import java.util.List;
import Model.Doctor;

/**
 *
 * @author PC
 */
public class BookmarkDAO {
    
    private final Connection conn;
    private final String bookmark = "SELECT First_Name,Last_Name,Hospital_ID \n" +
                                    "FROM doctor INNER JOIN doctor_bookmark \n" +
                                    "ON doctor.ID = doctor_bookmark.Doctor_ID WHERE Patient_ID = ?";
    private final String add = "INSERT INTO Hospital_Bookmark (?,?)";
    private final String delete_allbookmark = "DELECT FROM Hospital_Bookmark WHERE Patient_ID = ?";
    private final String delete_bookmark = "DELECT FROM Hospital_Bookmark WHERE Patient_ID = ? AND Hospital_ID = ?";

 public BookmarkDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
public void add(int patientID, int docID) throws SQLException{
    PreparedStatement ps = conn.prepareStatement(add);
    ps.setInt(1, patientID);
    ps.setInt(2, docID);
    ResultSet rs = ps.executeQuery();
}
public ArrayList<Doctor> getbookmark(int patientID) throws SQLException{
    ArrayList<Doctor> list = new ArrayList();
    PreparedStatement ps = conn.prepareStatement(bookmark);
    ps.setInt(1, patientID);
    ResultSet rs = ps.executeQuery();
    while(rs.next()){
            Doctor doc = new Doctor();
            doc.setFirstName(rs.getString("First_Name"));
            doc.setLastName(rs.getString("Last_Name"));
            doc.setHospitalId(rs.getInt("Hospital_ID"));
            list.add(doc);
        }
    return list;
}
public void delete(int patientID, int docID) throws SQLException{
    PreparedStatement ps = conn.prepareStatement(delete_bookmark);
    ps.setInt(1, patientID);
    ps.setInt(2, docID);
    ResultSet rs = ps.executeQuery();
}
public void deleteall(int patientID) throws SQLException{
    PreparedStatement ps = conn.prepareStatement(delete_bookmark);
    ps.setInt(1, patientID);
    ResultSet rs = ps.executeQuery();
}
}

