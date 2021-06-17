/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.roles;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RoleDTO implements Serializable{
    private int roleInt;
    private String roleName;

    public RoleDTO(int roleInt, String roleName) {
        this.roleInt = roleInt;
        this.roleName = roleName;
    }

    public int getRoleInt() {
        return roleInt;
    }

    public void setRoleInt(int roleInt) {
        this.roleInt = roleInt;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" + "roleInt=" + roleInt + ", roleName=" + roleName + '}';
    }
    
    
}
