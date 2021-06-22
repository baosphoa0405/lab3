/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private String LOAD_PRODUCT_SERVLET = "LoadProductServlet";
    private String LOGIN_SERVLET = "LoginServlet";
    private String LOGOUT_SERVLET = "LogoutServlet";
    private String DELETE_SERVLET = "DeleteProductServlet";
    private String INSERT_SERVLET = "InsertServlet";
    private String INSERTED_SERVLET = "InsertedServlet";
    private String GET_DETAIL_SERVLET = "GetDetailServlet";
    private String UPDATED_SERVLET = "UpdatedServlet";
    private String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private String VIEW_LIST_CART_SERVLET ="ViewListCartServlet";
    private String ADD_DELETE_TO_CART_SERVLET ="AddDeleteToCartServlet";
    private String CHECKOUT_SERVLET ="CheckoutServlet";
    private String VIEW_HISTORY_SERVLET ="ViewHistoryServlet";
    private String VIEW_DISCOUNT = "ViewDiscountServlet";
    private String CREATE_DISCOUNT = "CreateDiscountServlet";
    private String LOAD_PRODUCT_ADMIN = "LoadProductAdminServlet";
    private String ADD_DISCOUNT_SERVLET = "AddDiscountServlet";
    
    private String ACTION_LOGIN = "Login";
    private String ACTION_LOGOUT = "Logout";
    private String ACTION_RESET = "Reset";
    private String ACTION_DELETE = "Delete";
    private String ACTION_INSERT = "Insert";
    private String ACTION_SUBMIT_INSERT = "Submit Insert";
    private String ACTION_UPDATE = "Update";
    private String ACTION_UPDATED = "Updated";
    private String ACTION_ADD_TO_CART = "AddToCart";
    private String ACTION_VIEW_LIST_CART = "ViewListCart";
    private String ADD_DELETE_TO_CART = "AddDeleteToCart";
    private String ActionCheckout = "Checkout";
    private String Action_View_History = "ViewHistory";
    private String Action_Search_History = "Search History";
    private String Action_View_Discount = "ViewDiscount";
    private String Action_Create_Discount = "createDiscount";
    private String Action_load_product_admin = "loadAdmin";
    private String Action_ADD_DISCOUNT = "AddDiscount";
    private String Action_Search = "search";
    
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
        String btnAction = request.getParameter("btnAction");
        String url = "";
        try {
            if (btnAction == null) {
                url = LOAD_PRODUCT_SERVLET;
            }else if (btnAction.equals(Action_Search)) {
                url = LOAD_PRODUCT_SERVLET;
            }else if (btnAction.equals(ACTION_LOGIN)) {
                url = LOGIN_SERVLET;
            }else if (btnAction.equals(ACTION_LOGOUT)) {
                url = LOGOUT_SERVLET;
            }else if (btnAction.equals(ACTION_RESET)) {
                url = LOAD_PRODUCT_SERVLET;
            }else if (btnAction.equals(ACTION_DELETE)) {
                url = DELETE_SERVLET;
            }else if (btnAction.equals(ACTION_INSERT)) {
                url = INSERT_SERVLET;
            }else if (btnAction.equals(ACTION_SUBMIT_INSERT)) {
                url = INSERTED_SERVLET;
            }else if (btnAction.equals(ACTION_UPDATE)) {
                url = GET_DETAIL_SERVLET;
            }else if (btnAction.equals(ACTION_UPDATED)) {
                url = UPDATED_SERVLET;
            }else if (btnAction.equals(ACTION_ADD_TO_CART)) {
                url = ADD_TO_CART_SERVLET;
            }else if (btnAction.equals(ACTION_VIEW_LIST_CART)) {
                url = VIEW_LIST_CART_SERVLET;
            }else if (btnAction.equals(ADD_DELETE_TO_CART)) {
                url = ADD_DELETE_TO_CART_SERVLET;
            }else if (btnAction.equals(ActionCheckout)) {
                url = CHECKOUT_SERVLET;
            }else if (btnAction.equals(Action_View_History)) {
                url = VIEW_HISTORY_SERVLET;
            }else if (btnAction.equals(Action_Search_History)) {
                url = VIEW_HISTORY_SERVLET;
            }else if (btnAction.equals(Action_View_Discount)) {
                url = VIEW_DISCOUNT;
            }else if (btnAction.equals(Action_Create_Discount)){
                url = CREATE_DISCOUNT;
            }else if (btnAction.equals(Action_load_product_admin)) {
                url = LOAD_PRODUCT_ADMIN;
            }else if (btnAction.equals(Action_ADD_DISCOUNT)) {
                url = ADD_DISCOUNT_SERVLET;
            }
        } catch (Exception e) {
            log(e.getMessage());
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
