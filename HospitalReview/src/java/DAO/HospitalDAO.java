/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import DBConnection.DatabaseConnection;
import Model.*;

/**
 *
 * @author Negarr
 */
public class HospitalDAO {

    private Connection conn;
    
    public HospitalDAO() throws SQLException, ClassNotFoundException {
        conn = DatabaseConnection.getConnection();
    }
    
}
