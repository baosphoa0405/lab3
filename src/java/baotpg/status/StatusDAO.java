/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.status;

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
public class StatusDAO {

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
    
    public ArrayList<StatusDTO> getListStatus() throws NamingException, SQLException{
        ArrayList<StatusDTO> listStatus = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "select statusID, statusName from Status";
                pstm = cn.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {                    
                    listStatus.add(new StatusDTO(rs.getInt("statusID"), rs.getString("statusName")));
                }
            }
        } finally {
            close();
        }
        return listStatus;
    }
}
