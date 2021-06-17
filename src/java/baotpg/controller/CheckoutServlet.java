/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.books.BookDAO;
import baotpg.books.BookDTO;
import baotpg.cart.CartDTO;
import baotpg.histories.HistoryDAO;
import baotpg.histories.HistoryDTO;
import baotpg.historyDetails.HistoryDetailDAO;
import baotpg.historyDetails.HistoryDetailDTO;
import baotpg.users.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

    private String SUCCESSS = "LoadProductServlet";
    private String FAIL = "ViewListCartServlet";
    private String FAIL_OUT_OF_STOCK = "LoadProductServlet";

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
        String url = FAIL;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String dateOrder = request.getParameter("dateOrder");
            String dateShip = request.getParameter("dateShip");
            String total = request.getParameter("total");
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("account");
            HistoryDAO historyDao = new HistoryDAO();
            String mess = "";
            HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("listCart");
            Set<String> listKeys = cart.keySet();
            BookDAO bookDao = new BookDAO();
            boolean checkQuantity = false;
            String idBookOutOfStock = "";
            if (!dateShip.isEmpty()) {
                // kiem tra so luong trong kho trc
                for (String idBook : listKeys) {
                    BookDTO book = bookDao.getDetailBook(idBook);
                    if (book.getQuantity() < cart.get(idBook)) {
                        checkQuantity = true;
                        idBookOutOfStock = book.getBookID();
                        break;
                    }
                }
                if (checkQuantity) {
                    mess = "Sorry book out of stock " + idBookOutOfStock;
                    request.setAttribute("orderFail", mess);
                    session.removeAttribute("cart");
                    session.removeAttribute("listCart");
                    url = FAIL_OUT_OF_STOCK;
                } else {
                    boolean flag = historyDao.insertHistory(new HistoryDTO(0, Float.parseFloat(total),
                            Date.valueOf(dateOrder), Date.valueOf(dateShip), false, user));
                    int idCart = historyDao.getIDCartBy(user.getUserID());
                    HistoryDetailDAO historyDetailDao = new HistoryDetailDAO();

                    if (flag) {
                        for (String idBook : listKeys) {
                            HistoryDetailDTO historyDetail = new HistoryDetailDTO(new HistoryDTO(idCart, 0, null, null, false, user),
                                    new BookDTO(idBook, "", "", "", "", 0, null, null, 0, null), cart.get(idBook));
                            boolean isAdd = historyDetailDao.insertHistortyDetail(historyDetail);
                            if (isAdd) {
                                BookDTO book = bookDao.getDetailBook(idBook);
                                boolean isUpdateQuantity = bookDao.updateQuantityBook(idBook, book.getQuantity() - cart.get(idBook));
                                if (isUpdateQuantity) {
                                    mess = "order successfull";
                                }
                            }
                        }
                    }
                    if (mess.equals("order successfull")) {
                        url = SUCCESSS;
                        session.removeAttribute("cart");
                        session.removeAttribute("listCart");
                        request.setAttribute("orderSuccess", mess);
                    }
                }
            } else {
                request.setAttribute("errorDateShip", "please choose DateShip");
            }

        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            log(ex.getMessage());
        } finally {
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