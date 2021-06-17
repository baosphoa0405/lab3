/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.histories;

import baotpg.users.UserDTO;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class HistoryDTO implements Serializable {

    private int IDcart;
    private float totalPrice;
    private Date dateOrder, dateShip;
    private boolean isPayment;
    private UserDTO user;

    public HistoryDTO(int IDcart, float totalPrice, Date dateOrder, Date dateShip, boolean isPayment, UserDTO user) {
        this.IDcart = IDcart;
        this.totalPrice = totalPrice;
        this.dateOrder = dateOrder;
        this.dateShip = dateShip;
        this.isPayment = isPayment;
        this.user = user;
    }

    @Override
    public String toString() {
        return "CartDTO{" + "IDcart=" + IDcart + ", totalPrice=" + totalPrice + ", dateOrder=" + dateOrder + ", dateShip=" + dateShip + ", isPayment=" + isPayment + ", user=" + user + '}';
    }

    public int getIDcart() {
        return IDcart;
    }

    public void setIDcart(int IDcart) {
        this.IDcart = IDcart;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Date getDateShip() {
        return dateShip;
    }

    public void setDateShip(Date dateShip) {
        this.dateShip = dateShip;
    }

    public boolean isIsPayment() {
        return isPayment;
    }

    public void setIsPayment(boolean isPayment) {
        this.isPayment = isPayment;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
