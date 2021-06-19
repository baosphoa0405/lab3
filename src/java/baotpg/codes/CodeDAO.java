/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.codes;

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
public class CodeDAO {

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

    public ArrayList<CodesDTO> getListCode() throws SQLException, NamingException {
        ArrayList<CodesDTO> listCode = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select codeID, codeValue, createDate from Codes ";
                pstm = cn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    listCode.add(new CodesDTO(rs.getInt("codeID"), rs.getInt("codeValue"), rs.getDate("createDate")));
                }
            }
        } finally {
            close();
        }
        return listCode;
    }

    public CodesDTO checkCode(String codeID) throws SQLException, NamingException {
        CodesDTO code = null;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select codeID, codeValue, createDate from Codes where codeID = ? ";
                pstm = cn.prepareStatement(sql);
                pstm.setString(1, codeID);
                rs = pstm.executeQuery();
                if (rs.next()) {
                    code = new CodesDTO(rs.getInt("codeID"), rs.getInt("codeValue"), rs.getDate("createDate"));
                }
            }
        } finally {
            close();
        }
        return code;
    }

    public boolean insertCode(CodesDTO code) throws SQLException, NamingException {
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "insert into Codes(codeID, codeValue, createDate) values(?,?,?)";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, code.getCodeID());
                pstm.setInt(2, code.getCodeValue());
                pstm.setDate(3, code.getDate());
                flag = pstm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            close();
        }
        return flag;
    }

}
