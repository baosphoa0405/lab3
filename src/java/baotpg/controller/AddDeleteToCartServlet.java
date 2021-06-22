/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.books.BookDAO;
import baotpg.cart.CartDTO;
import baotpg.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
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
@WebServlet(name = "AddDeleteToCartServlet", urlPatterns = {"/AddDeleteToCartServlet"})
public class AddDeleteToCartServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String bookID = request.getParameter("bookID");
        String flag = request.getParameter("flag");
        String action = request.getParameter("Action");
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        HashMap<String, Integer> listCart = (HashMap<String, Integer>) session.getAttribute("listCart");
        BookDAO bookDAO = new BookDAO();
        if (action == null) {
            action = "";
        }
        if (action.equals("Removed")) {
            listCart.remove(bookID);
        } else {
            if (flag.equals("true")) {
                try {
                    // add
                    int quantity = bookDAO.checkQuantityBook(bookID);
                    if (listCart.get(bookID) >= quantity) {
                        // chửi
                        cart.addItemCart(bookID);
                        session.setAttribute("cart", cart);
                        session.setAttribute("listCart", cart.getList());
                        request.setAttribute("bookID", bookID);
                        request.setAttribute("mess", "sorry amount book " + quantity);
                    } else {
                        cart.addItemCart(bookID);
                    }
                } catch (NamingException ex) {
                    log(ex.getMessage());
                } catch (SQLException ex) {
                    log(ex.getMessage());
                }
            } else {
                // remove
                cart.removeItemCart(bookID);
            }
        }
        session.setAttribute("cart", cart);
        request.getRequestDispatcher("ViewListCartServlet").forward(request, response);
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
