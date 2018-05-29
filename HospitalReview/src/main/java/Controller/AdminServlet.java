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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hai06
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

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
            String query = request.getParameter("query");
            if (query == null) {
                return;
            }
            UserDAO udao = new UserDAO();
            User user = udao.authorization(request.getSession(), request.getCookies());
            if (user != null && user.getType().equals("Admin")) {
                if (query.equals("getAllDoctors")) {
                    try {
                        DoctorDAO dao = new DoctorDAO();
                        List<Doctor> doctorList = dao.getAllDoctors();
                        request.setAttribute("doctorList", doctorList);
                        request.getRequestDispatcher("/admincp/doctor.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (query.equals("getAllHospitals")) {
                    try {
                        HospitalDAO dao = new HospitalDAO();
                        List<Hospital> hospitalList = dao.getAllHospitals();
                        request.setAttribute("hospitalList", hospitalList);
                        request.getRequestDispatcher("/admincp/hospital.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (query.equals("getAllPatients")) {
                    try {
                        PatientDAO dao = new PatientDAO();
                        List<Patient> patientList = dao.getAllPatients();
                        request.setAttribute("patientList", patientList);
                        request.getRequestDispatcher("/admincp/patient.jsp").forward(request, response);
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else {
                response.sendRedirect("/");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
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
