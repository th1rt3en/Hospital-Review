/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DAO.PatientDAO;
import Model.Patient;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
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
@WebServlet(name = "PatientServlet", urlPatterns = {"/PatientServlet"})
public class PatientServlet extends HttpServlet {

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
        String query = request.getParameter("query");
        if (query == null) {
            return;
        }
        if (query.equals("register-patient")) {
            //http://localhost:8084/PatientServlet?query=register&email=hai07890@gmail.com&password=1234567&firstName=hai&lastName=pham&gender=male&languages=vn&address=hcm
            String email = request.getParameter("email");
            String password = request.getParameter("password");
                
            try {
                PatientDAO pdao = new PatientDAO();
                UserDAO udao = new UserDAO();
                try {
                    Patient patient = new Patient();
                    BeanUtils.populate(patient, request.getParameterMap());
                    User user = patient.getUser();
                    pdao.setAutoCommit(false);
                    udao.setAutoCommit(false);
                    user.setType("Patient");
                    user.setActivated(false);
                    int id = udao.insertUser(user);
                    patient.setId(id);
                    pdao.insertPatient(patient);
                    udao.commit();
                    pdao.commit();
                    
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    udao.rollback();
                    pdao.rollback();
                    Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    pdao.setAutoCommit(true);
                    udao.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (query.equals("update")) {
            try {
                PatientDAO pdao = new PatientDAO();
                UserDAO udao = new UserDAO();
                try {
                    Patient patient = new Patient();
                    BeanUtils.populate(patient, request.getParameterMap());
                    User user = patient.getUser();
                    pdao.setAutoCommit(false);
                    udao.setAutoCommit(false);
                    user.setType("Patient");
                    //
                    //user.setActivated(false);
                    udao.updateUser(user);
                    pdao.updatePatient(patient);
                    udao.commit();
                    pdao.commit();
                    
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    udao.rollback();
                    pdao.rollback();
                    Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    pdao.setAutoCommit(true);
                    udao.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (query.equals("delete")) {
            //http://localhost:8084/PatientServlet?query=delete&id=4
            try {
                Patient patient = new Patient();
                BeanUtils.populate(patient, request.getParameterMap());
                UserDAO udao = new UserDAO();
                udao.deleteUser(patient.getUser());
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public boolean validateEmail(String email) {
        return true;
    }
    
    public boolean validatePassword(String password) {
        return true;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
