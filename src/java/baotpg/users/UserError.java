/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.users;

/**
 *
 * @author Admin
 */
public class UserError {

    private String emptyUserID, emptyPassword;

    public UserError(String emptyUserID, String emptyPassword) {
        this.emptyUserID = emptyUserID;
        this.emptyPassword = emptyPassword;
    }

    @Override
    public String toString() {
        return "UserError{" + "emptyUserID=" + emptyUserID + ", emptyPassword=" + emptyPassword + '}';
    }

    public String getEmptyUserID() {
        return emptyUserID;
    }

    public void setEmptyUserID(String emptyUserID) {
        this.emptyUserID = emptyUserID;
    }

    public String getEmptyPassword() {
        return emptyPassword;
    }

    public void setEmptyPassword(String emptyPassword) {
        this.emptyPassword = emptyPassword;
    }

}
