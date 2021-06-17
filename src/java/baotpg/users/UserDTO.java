/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.users;

import baotpg.roles.RoleDTO;
import baotpg.status.StatusDTO;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class UserDTO implements Serializable {
    private String userID, userName, password;
    private RoleDTO role;
    private StatusDTO status;

    public UserDTO(String userID, String userName, String password, RoleDTO role, StatusDTO status) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userName=" + userName + ", password=" + password + ", role=" + role + ", status=" + status + '}';
    }
    
    
}
