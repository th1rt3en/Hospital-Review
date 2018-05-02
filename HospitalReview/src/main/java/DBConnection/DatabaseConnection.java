/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

import java.sql.*;
/**
 *
 * @author Negarr
 */
public class DatabaseConnection {
    
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/hospitalreview";
    private static String username = "hai07890";
    private static String password = "1107";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        if (DatabaseConnection.connection == null) {
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
    }
    
}
