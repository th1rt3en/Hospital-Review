/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.CommentDAO;
import Model.Comment;
import Model.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        String query = request.getParameter("query");
        if (query == null) {
            return;
        }
        if (query.equals("getDoctorComment")) {
            try {
                Doctor doctor = new Doctor();
                BeanUtils.populate(doctor, request.getParameterMap());
                CommentDAO dao = new CommentDAO();
                List<Comment> commentList = dao.getDoctorComments(doctor);
                request.setAttribute("commentList", commentList);
                request.getRequestDispatcher("/").forward(request, response);
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if (query.equals("insert")) {
            try {
                Comment comment = new Comment();
                BeanUtils.populate(comment, request.getParameterMap());
                CommentDAO dao = new CommentDAO();
                dao.insert(comment);
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (query.equals("update")) {
            try {
                Comment comment = new Comment();
                BeanUtils.populate(comment, request.getParameterMap());
                CommentDAO dao = new CommentDAO();
                dao.update(comment);
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (query.equals("delete")) {
            try {
                Comment comment = new Comment();
                BeanUtils.populate(comment, request.getParameterMap());
                CommentDAO dao = new CommentDAO();
                dao.delete(comment);
            } catch (IllegalAccessException | InvocationTargetException | SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
