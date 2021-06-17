/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.categories;

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
public class CategoriesDAO {

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

    public ArrayList<CategoriesDTO> getListCategory() throws NamingException, SQLException{
        ArrayList<CategoriesDTO> listCategory = new ArrayList<>();
        try {
            cn = DBHelper.makeConnection();
            String sql = "select categoryID, categoryName from Categories";
            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {                
                listCategory.add(new CategoriesDTO(rs.getInt("categoryID"), rs.getString("categoryName")));
            }
        }finally {
            close();
        }
        return  listCategory;
    }
}
