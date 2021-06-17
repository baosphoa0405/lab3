/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.categories;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CategoriesDTO implements Serializable{
    private int categoryID;
    private String categoryName;

    public CategoriesDTO(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + '}';
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
