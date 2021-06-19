/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.codeDetails;

import baotpg.utils.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class CodeDetailDAO {

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
    
    public boolean insertCodeDetail(CodeDetailDTO codeDetail) throws NamingException, SQLException{
        boolean flag = false;
        try {
            cn = DBHelper.makeConnection();
            if (cn != null) {
                String sql = "Insert into CodeDetail(codeID, userID, statusID) values(?,?,?) ";
                pstm = cn.prepareStatement(sql);
                pstm.setInt(1, codeDetail.getCode().getCodeID());
                pstm.setString(2, codeDetail.getUserID());
                pstm.setInt(3, codeDetail.getStatus().getStatusID());
                flag = pstm.executeUpdate() > 0 ? true : false;
                
            }
        } finally{
            close();
        }
        return flag;
    }
//    public boolean checkCodeDetail(CodeDetailDTO code ){
//        
//    }
}
