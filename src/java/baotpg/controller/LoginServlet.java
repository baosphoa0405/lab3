/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.users.UserDAO;
import baotpg.users.UserDTO;
import baotpg.users.UserError;
import baotpg.utils.MyContants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private String ERROR = "Login.jsp";
    private String URL_USER = "LoadProductServlet";
    private String URL_ADMIN = "LoadProductAdminServlet";
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
        String url = ERROR;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String userID = request.getParameter("userID");
            String password = request.getParameter("password");
            String messSuccess = "";
            String messError = "";
            HttpSession session = request.getSession();
            boolean flag = false;
            UserError error = new UserError("", "");
            if (userID.isEmpty()) {
                flag = true;
                error.setEmptyUserID("userID is empty");
            }
            if (password.isEmpty()) {
                flag = true;
                error.setEmptyPassword("password is empty");
            }
            if (flag) {
                url = ERROR;
                request.setAttribute("error", error);
            } else {
                UserDAO userDAO = new UserDAO();
                UserDTO account = userDAO.checkLogin(userID, password);
                if (account == null) {
                    messError = "userID: " + userID + " password: " + password + " wrong Login Fail";
                } else {
                    if (account.getStatus().getStatusName().equals(MyContants.STATUS_ACTIVE)) {
                        messSuccess = "Login Sucess";
                        session.setAttribute("account", account);
                        if (account.getRole().getRoleName().equals(MyContants.ROLE_USER)) {
                            url = URL_USER;
                        }else{
                            url = URL_ADMIN;
                        }
                    }else{
                        messError = "userID " + userID + " InActive";
                    }
                }
            }
            request.setAttribute("messSuccess", messSuccess);
            request.setAttribute("messError", messError);
            request.setAttribute("error", error);
            request.setAttribute("userID", userID);
            request.setAttribute("password", password);
        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            log(ex.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
