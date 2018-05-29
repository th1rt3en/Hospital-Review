/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.HospitalDAO;
import DAO.PatientDAO;
import DAO.UserDAO;
import Model.Hospital;
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
@WebServlet(name = "HospitalServlet", urlPatterns = {"/HospitalServlet"})
public class HospitalServlet extends HttpServlet {

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
        if (query.equals("register-hospital")) {
            //http://localhost:8084/HospitalServlet?query=register&email=hai07890@gmail.com&password=1234567&firstName=hai&lastName=pham&name=BV&address=chienthang&website=www.bv.com&hospitalId=1
            try {
                HospitalDAO hdao = new HospitalDAO();
                UserDAO udao = new UserDAO();
                try {
                    Hospital hospital = new Hospital();
                    BeanUtils.populate(hospital, request.getParameterMap());
                    User user = hospital.getUser();
                    hdao.setAutoCommit(false);
                    udao.setAutoCommit(false);
                    user.setType("Hospital");
                    user.setActivated(false);
                    int id = udao.insertUser(user);
                    hospital.setId(id);
                    hdao.insertHospital(hospital);
                    udao.commit();
                    hdao.commit();
                    
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    udao.rollback();
                    hdao.rollback();
                    Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    hdao.setAutoCommit(true);
                    udao.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (query.equals("update")) {
            //http://localhost:8084/HospitalServlet?query=update&id=5&email=hai07890@gmail.com&password=123456&firstName=hai&lastName=pham&name=BV&address=chien&website=www.bv.com&hospitalId=1
            try {
                HospitalDAO hdao = new HospitalDAO();
                UserDAO udao = new UserDAO();
                try {
                    Hospital hospital = new Hospital();
                    BeanUtils.populate(hospital, request.getParameterMap());
                    User user = hospital.getUser();
                    hdao.setAutoCommit(false);
                    udao.setAutoCommit(false);
                    //
                    user.setType("Hospital");
                    user.setActivated(false);
                    udao.updateUser(user);
                    hdao.updateHospital(hospital);
                    udao.commit();
                    hdao.commit();
                    
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    udao.rollback();
                    hdao.rollback();
                    Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    hdao.setAutoCommit(true);
                    udao.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (query.equals("delete")) {
            //http://localhost:8084/HospitalServlet?query=delete&id=5
            try {
                Hospital hospital = new Hospital();
                BeanUtils.populate(hospital, request.getParameterMap());
                UserDAO udao = new UserDAO();
                udao.deleteUser(hospital.getUser());
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(PatientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
