/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.books.BookDAO;
import baotpg.books.BookDTO;
import baotpg.codeDetails.CodeDetailDAO;
import baotpg.codeDetails.CodeDetailDTO;
import baotpg.codes.CodesDTO;
import baotpg.histories.HistoryDAO;
import baotpg.histories.HistoryDTO;
import baotpg.historyDetails.HistoryDetailDAO;
import baotpg.historyDetails.HistoryDetailDTO;
import baotpg.payment.PaymentServices;
import baotpg.status.StatusDTO;
import baotpg.users.UserDTO;
import baotpg.utils.MyContants;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
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
@WebServlet(name = "ReviewPayment", urlPatterns = {"/ReviewPayment"})
public class ReviewPayment extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
         BookDAO bookDao = new BookDAO();
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("account");
        String mess = "";
        HashMap<String, Integer> cart = (HashMap<String, Integer>) session.getAttribute("listCart");
        Set<String> listKeys = cart.keySet();
        HistoryDAO historyDao = new HistoryDAO();
        HistoryDTO history = (HistoryDTO) session.getAttribute("history");
        try {
            history.setIsPayment(true);
            history.setTotalPrice((float)Math.round(history.getTotalPrice() * 100) / 100);
            boolean flag = historyDao.insertHistory(history);
            // insert codeDetail
            CodeDetailDAO codeDetail = new CodeDetailDAO();
            if (history.getCode() != null) {
                boolean updateCodeDetail = codeDetail.updateCodeDetail(new CodeDetailDTO(history.getCode(), user.getUserID(),
                        new StatusDTO(MyContants.STATUS_NUMBER_INACTIVE, "")));
            }
            int idCart = historyDao.getIDCartBy(user.getUserID());
            HistoryDetailDAO historyDetailDao = new HistoryDetailDAO();

            if (flag) {
                for (String idBook : listKeys) {
                    HistoryDetailDTO historyDetail = new HistoryDetailDTO(new HistoryDTO(idCart, 0, null, null, false, user, history.getCode()),
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
                session.removeAttribute("cart");
                session.removeAttribute("listCart");
            }

            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.getPaymentDetails(paymentId);

            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();

            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("shippingAddress", shippingAddress);
            request.setAttribute("listItem", transaction.getItemList());
            request.setAttribute("orderSuccess", "order successfull");
            String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;

            request.getRequestDispatcher(url).forward(request, response);
        } catch (PayPalRESTException ex) {
            log(ex.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
            log(ex.getMessage());
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
