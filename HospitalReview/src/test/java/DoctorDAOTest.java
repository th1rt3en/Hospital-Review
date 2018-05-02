
import DAO.DoctorDAO;
import Model.Doctor;
import DAO.UserDAO;
import Model.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.TestCase.fail;
import junit.framework.TestCase;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hai06
 */
public class DoctorDAOTest extends TestCase {

    @Test
    public void testInsert() {
        try {
            //UserDAO u = new UserDAO();
            //User user = new User("hai06780@gmail.com", "1107", true, 3);
            //u.insertUser(user);
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = new Doctor("hai", "pham", "male", "bachelor", true, "9-5", "VN-EN", 1);
            dao.insertDoctor(doc);
        } catch (Exception ex) {
            System.out.print(ex);
            fail();
            
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(DoctorDAOTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
