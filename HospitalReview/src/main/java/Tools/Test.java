/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import DAO.*;
import Model.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Negarr
 */
public class Test {

    public static void main(String[] args) {
        User u = new User();
        u.setEmail("test@gmail.com");
        u.setPassword("123");
        u.setType("Hospital");

        Doctor d = new Doctor();
        d.setFirstName("Thong");
        d.setLastName("Bui");
        d.setGender("male");
        d.setDegree("Master");
        d.setLanguages("English");
        d.setHospitalId(1);
        d.setOfficeHours("9AM - 5PM");

        Specialty s = new Specialty();
        s.setGeneralName("Surgery");
        s.setSpecificName("Brain Surgery");
        
        try {
            UserDAO uDAO = new UserDAO();
            PatientDAO pDAO = new PatientDAO();
            HospitalDAO hDAO = new HospitalDAO();
            AdminDAO aDAO = new AdminDAO();
            DoctorDAO dDAO = new DoctorDAO();
            SpecialtyDAO sDAO = new SpecialtyDAO();

//            u.setId(uDAO.insertUser(u));

//            Patient p = new Patient(u);
//            p.setFirstName("Thong");
//            p.setLastName("Bui");
//            p.setGender("male");
//            p.setAddress("234 Pasteur");
//            p.setLanguages("English");
//            pDAO.insertPatient(p);

//            Hospital h = new Hospital(u);
//            h.setAddress("234 Pasteur");
//            h.setName("Pasteur");
//            hDAO.insertHospital(h);

//            Admin a = new Admin(u);
//            a.setFirstName("Thong");
//            a.setLastName("Bui");
//            aDAO.insertAdmin(a);

//            d.setId(dDAO.insertDoctor(d));

            s.setGeneralId(sDAO.insertGeneralSpecialty(s));
            s.setSpecificId(sDAO.insertSpecificSpecialty(s));
            sDAO.insertAssociation(s);

            

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
