/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.historyDetails;

import baotpg.books.BookDTO;
import baotpg.categories.CategoriesDTO;
import baotpg.histories.HistoryDTO;
import baotpg.status.StatusDTO;
import baotpg.users.UserDTO;
import baotpg.utils.DBHelper;
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
public class HistoryDetailDAO {

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

    public boolean insertHistortyDetail(HistoryDetailDTO historyDetail) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert into HistoryDetails(IDcart,quantity,bookID) "
                        + "Values(?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, historyDetail.getCart().getIDcart());
                pstm.setInt(2, historyDetail.getQuantity());
                pstm.setString(3, historyDetail.getBook().getBookID());
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }

    public ArrayList<HistoryDetailDTO> getListHistoryDetail(String userID, String title, String dateOrder) throws SQLException, NamingException {
        ArrayList<HistoryDetailDTO> listHistory = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            String sqlCondition = "";
            if (!title.isEmpty()) {
                sqlCondition = "and b.title like ? ";
            }
            if (!dateOrder.isEmpty()) {
                sqlCondition = "and h.dateOrder = ? ";
            }
            if (!title.isEmpty() && !dateOrder.isEmpty()) {
                sqlCondition = "and b.title like ? and h.dateOrder = ? ";
            }
            if (cn != null) {
                String sql = "select h.IDcart, totalPrice, dateOrder, dateShip, isPayment, "
                        + "h.userID, b.title, b.image, b.bookID, b.price, hd.quantity,  b.statusID, s.statusName, c.categoryName, c.categoryID "
                        + "from Histories h, HistoryDetails hd, Books b, Users u, Status s, Categories c \n"
                        + "where h.IDcart = hd.IDcart "
                        + "and h.userID = u.userID "
                        + "and hd.bookID = b.bookID "
                        + "and b.statusID = s.statusID "
                        + "and b.categoryID = c.categoryID "
                        + "and h.userID = ? " + sqlCondition;
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, userID);
                if (!title.isEmpty()) {
                    pstm.setString(2, "%" + title + "%");
                }
                if (!dateOrder.isEmpty()) {
                    pstm.setString(2, dateOrder);
                }
                if (!title.isEmpty() && !dateOrder.isEmpty()) {
                    pstm.setString(2, "%" + title + "%");
                    pstm.setString(2, dateOrder);
                }
                rs = pstm.executeQuery();
                while (rs.next()) {
                    StatusDTO status = new StatusDTO(rs.getInt("statusID"), rs.getString("statusName"));
                    CategoriesDTO category = new CategoriesDTO(rs.getInt("categoryID"), rs.getString("categoryName"));
                    BookDTO book = new BookDTO(rs.getString("BookID"), rs.getString("title"), rs.getString("image"),
                            "", "", 0, status, category, rs.getFloat("price"), null);
                    UserDTO user = new UserDTO(rs.getString("userID"), "", "", null, null);
                    HistoryDTO history = new HistoryDTO(rs.getInt("IDcart"), rs.getFloat("totalPrice"),
                            rs.getDate("dateOrder"), rs.getDate("dateShip"), rs.getBoolean("isPayment"), user);
                    HistoryDetailDTO historyDetail = new HistoryDetailDTO(history, book, rs.getInt("quantity"));
                    listHistory.add(historyDetail);
                }
            }
        } finally {
            close();
        }
        return listHistory;
    }
}
