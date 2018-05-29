
import DAO.DoctorDAO;
import Model.Doctor;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hai06
 */


public class DoctorTest {
    public static void main(String args[]) {
        try {
//            DoctorDAO dao = new DoctorDAO();
//            List<Doctor> doctorList = dao.searchDoctors(new Doctor());
//            Logger.getLogger(DoctorTest.class.getName()).info("sfds");
//            for (Doctor doctor : doctorList) {
//                Logger.getLogger(DoctorTest.class.getName()).info(doctor.toString());
//            }
            DoctorDAO dao = new DoctorDAO(); 
            Doctor doctor = new Doctor();
            doctor.setFirstName("hai");
            doctor.setLastName("pham");
            doctor.setGender("male");
            doctor.setDegree("bachelor");
            doctor.setHospitalId(1);
            doctor.setId(1);
            doctor.setLanguages("vn");
            doctor.setOfficeHours("xxx");
            doctor.setAcceptedInsurance(true);
            dao.updateDoctor(doctor);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoctorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
