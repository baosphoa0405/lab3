/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.books;

import baotpg.categories.CategoriesDTO;
import baotpg.status.StatusDTO;
import baotpg.utils.DBHelper;
import baotpg.utils.MyContants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class BookDAO {

    private PreparedStatement pstm = null;
    private Connection cn = null;
    private ResultSet rs = null;

    public void close() throws SQLException {
        if (pstm != null) {
            pstm.close();
        }
        if (cn != null) {
            cn.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public BookDTO getDetailBook(String bookID) throws SQLException, NamingException {
        BookDTO book = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select bookID, title, image, description, price, author, c.categoryID, categoryName, quantity, statusName, s.statusID, date \n"
                        + "from Books b, Status s, Categories c where b.statusID = s.statusID "
                        + "and b.categoryID = c.categoryID "
                        + "and bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, bookID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    book = new BookDTO(rs.getString("bookID"), rs.getString("title"), rs.getString("image"),
                            rs.getString("description"), rs.getString("author"), rs.getInt("quantity"), new StatusDTO(rs.getInt("statusID"),
                            rs.getString("statusName")), new CategoriesDTO(rs.getInt("categoryID"), rs.getString("categoryName")), rs.getFloat("price"), rs.getDate("date"));
                }
            }
        } finally {
            close();
        }
        return book;
    }
    public int checkQuantityBook(String bookID) throws NamingException, SQLException{
        int quantity = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select quantity from Books where bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, bookID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } finally {
            close();
        }
        return quantity;
    }
    public boolean updateBook(BookDTO book) throws SQLException, NamingException{
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Update Books SET title = ?, image = ?, description = ?, price = ?, author = ?, "
                        + "categoryID = ?, statusID = ?, quantity = ?, date = ? where bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, book.getTitle());
                pstm.setString(2, book.getImage());
                pstm.setString(3, book.getDescription());
                pstm.setFloat(4, book.getPrice());
                pstm.setString(5, book.getAuthor());
                pstm.setInt(6, book.getCategory().getCategoryID());
                pstm.setInt(7, book.getStatus().getStatusID());
                pstm.setInt(8, book.getQuantity());
                pstm.setDate(9, book.getDate());
                pstm.setString(10, book.getBookID());
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        }finally{
            close();
        }
        return flag;
    }

    public boolean insertBook(BookDTO book) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert into Books values(?,?,?,?,?,?,?,?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, book.getBookID());
                pstm.setString(2, book.getTitle());
                pstm.setString(3, book.getImage());
                pstm.setString(4, book.getDescription());
                pstm.setFloat(5, book.getPrice());
                pstm.setString(6, book.getAuthor());
                pstm.setInt(7, book.getCategory().getCategoryID());
                pstm.setInt(8, book.getStatus().getStatusID());
                pstm.setInt(9, book.getQuantity());
                pstm.setDate(10, book.getDate());
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }

    public boolean updateStatusBook(String bookID, int status) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "update Books set statusID = ? where bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, status);
                pstm.setString(2, bookID);
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }
    
     public boolean updateQuantityBook(String bookID, int quantity) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "update Books set quantity = ? where bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, quantity);
                pstm.setString(2, bookID);
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }

    public ArrayList<BookDTO> getListBook(String title, String categoryName, String min, String max) throws NamingException, SQLException {
        ArrayList<BookDTO> list = new ArrayList<>();
        String sqlCondition = "";
        if (!categoryName.isEmpty()) {
            sqlCondition = "and c.categoryName = ? ";
        }
        if (!min.isEmpty() && !max.isEmpty()) {
            sqlCondition = "and price BETWEEN ? and ? ";
        }
        if (!categoryName.isEmpty() && !min.isEmpty() && !max.isEmpty()) {
            sqlCondition = "and categoryName = ? and price BETWEEN ? and ? ";
        }
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select bookID, title, image, description, price, author, c.categoryID, categoryName, quantity, statusName, s.statusID, date \n"
                        + "from Books b, Status s, Categories c where b.statusID = s.statusID "
                        + "and b.categoryID = c.categoryID "
                        + "and statusName = ? and quantity > 0 "
                        + "and title like ? " + sqlCondition;
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, MyContants.STATUS_ACTIVE);
                pstm.setString(2, "%" + title + "%");
                if (!categoryName.isEmpty()) {
                    pstm.setString(3, categoryName);
                }
                if (!min.isEmpty() && !max.isEmpty()) {
                    pstm.setFloat(3, Float.parseFloat(min));
                    pstm.setFloat(4, Float.parseFloat(max));
                }
                if (!categoryName.isEmpty() && !min.isEmpty() && !max.isEmpty()) {
                    pstm.setString(3, categoryName);
                    pstm.setFloat(4, Float.parseFloat(min));
                    pstm.setFloat(5, Float.parseFloat(max));
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                    list.add(new BookDTO(rs.getString("bookID"), rs.getString("title"), rs.getString("image"),
                            rs.getString("description"), rs.getString("author"), rs.getInt("quantity"), new StatusDTO(rs.getInt("statusID"),
                            rs.getString("statusName")), new CategoriesDTO(rs.getInt("categoryID"), rs.getString("categoryName")), rs.getFloat("price"), rs.getDate("date")));
                }
            }
        } finally {
            close();
        }
        return list;
    }

    public ArrayList<BookDTO> getListBookAdmin() throws NamingException, SQLException {
        ArrayList<BookDTO> list = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select bookID, title, image, description, price, author, c.categoryID, categoryName, quantity, statusName, s.statusID, date \n"
                        + "from Books b, Status s, Categories c where b.statusID = s.statusID "
                        + "and b.categoryID = c.categoryID ";
                pstm = cn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    list.add(new BookDTO(rs.getString("bookID"), rs.getString("title"), rs.getString("image"),
                            rs.getString("description"), rs.getString("author"), rs.getInt("quantity"), new StatusDTO(rs.getInt("statusID"),
                            rs.getString("statusName")), new CategoriesDTO(rs.getInt("categoryID"), rs.getString("categoryName")), rs.getFloat("price"), rs.getDate("date")));
                }
            }
        } finally {
            close();
        }
        return list;
    }

    public boolean checkDuplicateBook(String bookID) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select bookID from Books where bookID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, bookID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    flag = rs.getString("bookID").isEmpty() ? false : true;
                }
            }

        } finally {
            close();
        }
        return flag;
    }
}
