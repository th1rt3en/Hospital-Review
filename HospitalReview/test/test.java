/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.*;
import Model.*;
import DBConnection.DatabaseConnection;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Negarr
 */
public class test {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        PatientDAO pDAO = new PatientDAO();
        Patient p = new Patient("Thong", "Bui", "Male", "nghoang190711@gmail.com", "1107", "234 Pasteur", "English");
        p.setId(pDAO.addPatient(p));

        List<Patient> patients = pDAO.getAllPatients();
        for (Patient patient : patients) {
            System.out.println(patient.getFirstName() + " " + patient.getLastName() + " " + patient.getPassword());
        }
        
        pDAO.updatePassword(p, "1597");

        patients = pDAO.getAllPatients();
        for (Patient patient : patients) {
            System.out.println(patient.getFirstName() + " " + patient.getLastName() + " " + patient.getPassword());
        }
    }

}
