/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.status;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class StatusDTO implements Serializable {

    private int statusID;
    private String statusName;

    public StatusDTO(int statusID, String statusName) {
        this.statusID = statusID;
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "Status{" + "statusID=" + statusID + ", statusName=" + statusName + '}';
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}
