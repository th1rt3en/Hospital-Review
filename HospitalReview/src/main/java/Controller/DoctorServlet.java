/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DoctorDAO;
import DAO.UserDAO;
import Model.Doctor;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Negarr
 */
@WebServlet(name = "DoctorServlet", urlPatterns = {"/DoctorServlet"})
public class DoctorServlet extends HttpServlet {

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
            UserDAO udao = new UserDAO();
            User user = udao.authorization(request.getSession(), request.getCookies());
            String query = request.getParameter("query");
            if (query == null) {
                return;
            }
            //http://localhost:8084/DoctorServlet?query=search&firstName=sdf&lastName=dsf&gender=gdsf&degree=bachelor
            if (query.equals("search")) {
                Doctor doctor = new Doctor();
                try {
                    BeanUtils.populate(doctor, request.getParameterMap());
                    DoctorDAO dao = new DoctorDAO();
                    List<Doctor> doctorList = dao.searchDoctors(doctor);
                    request.setAttribute("doctorList", doctorList);
                    request.getRequestDispatcher("/search_doctor.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException | IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(DoctorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (query.equals("insert") && user != null && (user.getType().equals("Hospital") || user.getType().equals("Admin"))) {
                //http://localhost:8084/DoctorServlet?query=insert&firstName=hai&lastName=pham1&gender=male&languages=vn&degree=master&acceptedInsurance=0&officeHours=xxx&hospitalId=1
                Doctor doctor = new Doctor();
                try {
                    BeanUtils.populate(doctor, request.getParameterMap());
                    if (user.getType().equals("Hospital")) {
                        doctor.setHospitalId(user.getId());
                    }
                    DoctorDAO dao = new DoctorDAO();
                    int id = dao.insertDoctor(doctor);
                    doctor.setId(id);
                    request.setAttribute("newDoctor", doctor);
                    response.getWriter().write("successfully added");
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (query.equals("update") && user != null && (user.getType().equals("Hospital") || user.getType().equals("Admin"))) {
                //http://localhost:8084/DoctorServlet?query=update&id=1&firstName=hai&lastName=pham1&gender=male&languages=vn&degree=master&acceptedInsurance=0&officeHours=xxx&hospitalId=1
                Doctor doctor = new Doctor();
                try {
                    BeanUtils.populate(doctor, request.getParameterMap());
                    if (user.getType().equals("Hospital") && doctor.getHospitalId() != user.getId()) {
                        response.sendRedirect("/");
                    }
                    else {
                        DoctorDAO dao = new DoctorDAO();
                        dao.updateDoctor(doctor);
                        request.setAttribute("newDoctor", doctor);
                        response.getWriter().write("successfully updated");
                    }
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (query.equals("delete") && user != null && (user.getType().equals("Hospital") || user.getType().equals("Admin"))) {
                //http://localhost:8084/DoctorServlet?query=delete&id=1
                Doctor doctor = new Doctor();
                try {
                    BeanUtils.populate(doctor, request.getParameterMap());
                    if (user.getType().equals("Hospital") && doctor.getHospitalId() != user.getId()) {
                        response.getWriter().write("You can't delete doctor of another hospital");
                    }
                    else {
                        DoctorDAO dao = new DoctorDAO();
                        dao.deleteDoctor(doctor);
                        response.getWriter().write("successfully deleted");
                    }
                } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(DoctorServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DoctorServlet.class.getName()).log(Level.SEVERE, null, ex);
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
