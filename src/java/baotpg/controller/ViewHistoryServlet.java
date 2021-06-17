/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.histories.HistoryDAO;
import baotpg.historyDetails.HistoryDetailDAO;
import baotpg.historyDetails.HistoryDetailDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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

/**
 *
 * @author Admin
 */
@WebServlet(name = "ViewHistoryServlet", urlPatterns = {"/ViewHistoryServlet"})
public class ViewHistoryServlet extends HttpServlet {

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
        ArrayList<HistoryDetailDTO> listHistoryDetail = null;
        ArrayList<Integer> listIDcartHistory = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            String UserID = request.getParameter("UserID");
            String titleSearch = request.getParameter("title");
            String dateBooking = request.getParameter("dateBooking");
            HistoryDetailDAO historyDetail = new HistoryDetailDAO();
            HistoryDAO historyDao = new HistoryDAO();
            if (titleSearch == null) {
                titleSearch = "";
            }
            if (dateBooking == null) {
                dateBooking = "";
            }
            listHistoryDetail = historyDetail.getListHistoryDetail(UserID, titleSearch, dateBooking);
            listIDcartHistory = historyDao.getIDCart(UserID);
            HashMap<Integer, ArrayList<HistoryDetailDTO>> map = new HashMap<>();
            for (Integer item : listIDcartHistory) {
                map.put(item, null);
            }
            Set<Integer> listKey = map.keySet();
            for (Integer key : listKey) {
                for (HistoryDetailDTO item : listHistoryDetail) {
                    if (item.getCart().getIDcart() == key) {
                        ArrayList<HistoryDetailDTO> a = map.get(key);
                        if (a == null) {
                            a = new ArrayList<>();
                            a.add(item);
                        } else {
                            a.add(item);
                        }
                        map.put(key, a);
                    }
                }
            }
            request.setAttribute("listHistoryMap", map);
            request.setAttribute("title", titleSearch);
            request.setAttribute("dateBooking", dateBooking);
        } catch (SQLException ex) {
            Logger.getLogger(ViewHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ViewHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher("ViewListHistory.jsp").forward(request, response);
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
