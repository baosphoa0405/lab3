/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.historyDetails;

import baotpg.books.BookDTO;
import baotpg.histories.HistoryDTO;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class HistoryDetailDTO implements Serializable{
    private HistoryDTO cart;
    private BookDTO book;
    private int quantity;

    @Override
    public String toString() {
        return "CartDetailDTO{" + "cart=" + cart + ", book=" + book + ", quantity=" + quantity + '}';
    }

    public HistoryDTO getCart() {
        return cart;
    }

    public void setCart(HistoryDTO cart) {
        this.cart = cart;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public HistoryDetailDTO(HistoryDTO cart, BookDTO book, int quantity) {
        this.cart = cart;
        this.book = book;
        this.quantity = quantity;
    }
    
}
