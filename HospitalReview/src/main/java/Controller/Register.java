/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DoctorDAO;
import DAO.HospitalDAO;
import DAO.PatientDAO;
import DAO.UserDAO;
import Model.Doctor;
import Model.Hospital;
import Model.Patient;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author hai06
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int type = Integer.parseInt(request.getParameter("Type"));
            Map map = request.getParameterMap();
            
            if (type == 1) {
                User user = new User();
                BeanUtils.populate(user, map);
                Patient patient = new Patient();
                BeanUtils.populate(patient, map);
                PatientDAO pDao = new PatientDAO();
                UserDAO uDao = new UserDAO();
                uDao.insertUser(user);
                patient.setId(user.getId());
                pDao.addPatient(patient);
            }
            else if (type == 2) {
                User user = new User();
                BeanUtils.populate(user, map);
                Hospital hospital = new Hospital();
                BeanUtils.populate(hospital, map);
                HospitalDAO hDao = new HospitalDAO();
                UserDAO uDao = new UserDAO();
                uDao.insertUser(user);
                hospital.setId(user.getId());
                hDao.insertHospital(hospital);
            }
            else if (type == 3) {
                UserDAO dao = new UserDAO();
                User curUser = (User) request.getSession().getAttribute("User");
                if (dao.authorization(curUser) == 3) {
                    User user = new User();
                    BeanUtils.populate(user, map);
                    user.setType(3);
                    UserDAO uDao = new UserDAO();
                    uDao.insertUser(user);
                }
            }
        }   catch (IllegalAccessException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
