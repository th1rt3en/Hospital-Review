/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBConnection.DatabaseConnection;
import Model.Comment;
import Model.Doctor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hai06
 */
public class CommentDAO {
    
    private final Connection conn;
    private static final String INSERT_COMMENT_STATEMENT = "INSERT INTO `doctor-comment`"
            + "(Comment, Rating, Enabled, Time, Doctor_ID, Patient_ID) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String LAST_INSERT_STATEMENT = "SELECT LAST_INSERT_ID()";
    private static final String UPDATE_COMMENT_STATEMENT = "UPDATE `doctor-comment` "
            + "SET Comment = ?, Rating = ?, Enabled = ?, Time = ?, Doctor_ID = ?, Patient_ID = ? "
            + "WHERE ID = ?";
    private static final String DELETE_COMMENT_STATEMENT = "DELETE FROM `doctor-comment` WHERE ID = ?";
    private static final String GET_DOCTOR_COMMENTS_STATEMENT = "SELECT * FROM `doctor-comment` WHERE Doctor_ID = ?";
    
    public CommentDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
    public List<Comment> getDoctorComments(Doctor doctor) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT_COMMENT_STATEMENT);
            ps.setInt(1, doctor.getId());
            ResultSet rs = ps.executeQuery();
            List<Comment> commentList = new ArrayList<>();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt(1));
                comment.setComment(rs.getString(2));
                comment.setRating(rs.getInt(3));
                comment.setEnabled(rs.getBoolean(4));
                comment.setTime(rs.getDate(5));
                comment.setDoctorID(rs.getInt(6));
                comment.setPatientID(rs.getInt(7));
                commentList.add(comment);
            }
            return commentList;
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int insert(Comment comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(INSERT_COMMENT_STATEMENT);
        ps.setString(1, comment.getComment());
        ps.setInt(2, comment.getRating());
        ps.setBoolean(3, comment.isEnabled());
        ps.setDate(4, comment.getTime());
        ps.setInt(5, comment.getDoctorID());
        ps.setInt(6, comment.getPatientID());
        ps.executeUpdate();
        ps = conn.prepareStatement(LAST_INSERT_STATEMENT);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    
    public void update(Comment comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(UPDATE_COMMENT_STATEMENT);
        ps.setString(1, comment.getComment());
        ps.setInt(2, comment.getRating());
        ps.setBoolean(3, comment.isEnabled());
        ps.setDate(4, comment.getTime());
        ps.setInt(5, comment.getDoctorID());
        ps.setInt(6, comment.getPatientID());
        ps.setInt(7, comment.getId());
        ps.executeUpdate();
    }
    
    public void delete(Comment comment) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(DELETE_COMMENT_STATEMENT);
        ps.setInt(1, comment.getId());
        ps.executeUpdate();
    }
    
    public static void main(String args[]) {
        try {
            Comment comment = new Comment(1, "comment", 5, true, Date.valueOf("1997-09-10"), 2, 6);
            CommentDAO dao = new CommentDAO();
            dao.insert(comment);
            comment.setComment("new comment");
            dao.update(comment);
            dao.delete(comment);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
