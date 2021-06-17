/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.controller;

import baotpg.books.BookDAO;
import baotpg.books.BookDTO;
import baotpg.books.BookError;
import baotpg.categories.CategoriesDAO;
import baotpg.categories.CategoriesDTO;
import baotpg.status.StatusDAO;
import baotpg.status.StatusDTO;
import baotpg.utils.MyContants;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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
@WebServlet(name = "UpdatedServlet", urlPatterns = {"/UpdatedServlet"})
public class UpdatedServlet extends HttpServlet {

    private String FAIL = "DetailBook.jsp";
    private String SUCCESS = "LoadProductAdminServlet";

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
            String bookID = request.getParameter("bookID");
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String image = request.getParameter("image");
            String description = request.getParameter("description");
            String price = request.getParameter("price");
            String quanity = request.getParameter("quantity");
            String categoryID = request.getParameter("categoryID");
            String date = request.getParameter("date");
            String status = request.getParameter("status");

            BookError error = new BookError("", "", "", "", "", "", "", "", "", "", "", "", "", "");
            boolean flag = false;
            BookDAO bookDao = new BookDAO();

            if (title.isEmpty()) {
                flag = true;
                error.setTitleEmpty("title is empty");
            }
            if (!title.isEmpty() && title.length() > 100) {
                flag = true;
                error.setTitleLength("title only 100 character");
            }

            if (author.isEmpty()) {
                flag = true;
                error.setAuthorEmpty("author is empty");
            }
            if (!author.isEmpty() && author.length() > 100) {
                flag = true;
                error.setAuthorLength("author only 100 character");
            }
            if (image.isEmpty()) {
                flag = true;
                error.setImageEmpty("image is empty");
            }
            // thieu61 format
            if (description.isEmpty()) {
                flag = true;
                error.setDescriptionEmpty("description is empty");
            }
            if (!description.isEmpty() && description.length() > 100) {
                flag = true;
                error.setDescriptionLength("description only 100 character");
            }
            if (price.isEmpty()) {
                flag = true;
                error.setPriceEmpty("price is empty");
            }
            if (quanity.isEmpty()) {
                flag = true;
                error.setQuanityEmpty("quanlity is empty");
            }
            String mess = "";
            if (flag) {
                request.setAttribute("bookID", bookID);
                request.setAttribute("title", title);
                request.setAttribute("author", author);
                request.setAttribute("desciption", description);
                request.setAttribute("price", price);
                request.setAttribute("quantity", quanity);
                request.setAttribute("categoryID", categoryID);
                request.setAttribute("date", date);
                request.setAttribute("status", status);
                request.setAttribute("error", error);
            } else {
                BookDTO updateBook = new BookDTO(bookID, title, image, description, author, Integer.parseInt(quanity),
                        null, null, Float.parseFloat(price), Date.valueOf(date));
                StatusDAO statusDAO = new StatusDAO();
                for (StatusDTO s : statusDAO.getListStatus()) {
                    if (s.getStatusName().equals(status)) {
                        updateBook.setStatus(s);
                        break;
                    }
                }

                CategoriesDAO categoryDAO = new CategoriesDAO();
                for (CategoriesDTO categoriesDTO : categoryDAO.getListCategory()) {
                    if (categoriesDTO.getCategoryID() == Integer.parseInt(categoryID)) {
                        updateBook.setCategory(categoriesDTO);
                        break;
                    }
                }
                boolean isUpdate = bookDao.updateBook(updateBook);

                if (isUpdate) {
                    url = SUCCESS;
                    mess = "update success " + bookID;
                } else {
                    mess = "update fail" + bookID;
                }

            }
            request.setAttribute("bookID", bookID);
            request.setAttribute("mess", mess);
        } catch (NamingException ex) {
            log(ex.getMessage());
        } catch (SQLException ex) {
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
