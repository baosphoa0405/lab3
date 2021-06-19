/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.codes;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class CodesDTO {

    private int codeID, codeValue;
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CodesDTO(int codeID, int codeValue, Date createDate) {
        this.codeID = codeID;
        this.codeValue = codeValue;
        this.createDate = createDate;
    }

    public Date getDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "CodesDTO{" + "codeID=" + codeID + ", codeValue=" + codeValue + ", createDate=" + createDate + '}';
    }

    public void setDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCodeID() {
        return codeID;
    }

    public void setCodeID(int codeID) {
        this.codeID = codeID;
    }

    public int getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(int codeValue) {
        this.codeValue = codeValue;
    }

}
