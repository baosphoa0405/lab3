/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.histories;

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
public class HistoryDAO {

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

    public boolean insertHistory(HistoryDTO history) throws NamingException, SQLException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert into Histories(totalPrice, dateOrder, dateShip, isPayment, userID, codeID) "
                        + "Values (?,?,?,?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setFloat(1, history.getTotalPrice());
                pstm.setDate(2, history.getDateOrder());
                pstm.setDate(3, history.getDateShip());
                pstm.setBoolean(4, history.isIsPayment());
                pstm.setString(5, history.getUser().getUserID());
                pstm.setInt(6, history.getCode().getCodeID());
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }

    public int getIDCartBy(String userID) throws SQLException, NamingException {
        int id = 0;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select IDcart from Histories where userID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, userID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("IDcart");
                }
            }
        } finally {
            close();
        }
        return id;
    }

    public ArrayList<Integer> getIDCart(String userID) throws NamingException, SQLException {
        ArrayList<Integer> listIDCart = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select IDcart from Histories where userID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, userID);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listIDCart.add(rs.getInt("IDcart"));
                }
            }
        } finally {
            close();
        }
        return listIDCart;
    }

//    IDcart, totalPrice, dateOrder, dateShip, isPayment, userID
}
