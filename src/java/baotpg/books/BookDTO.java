/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.books;

import baotpg.categories.CategoriesDTO;
import baotpg.status.StatusDTO;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class BookDTO implements Serializable{

    private String bookID, title, image, description, author;
    private StatusDTO status;
    private CategoriesDTO category;
    private int quantity;
    private float price;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public CategoriesDTO getCategory() {
        return category;
    }

    public void setCategory(CategoriesDTO category) {
        this.category = category;
    }

    public BookDTO(String bookID, String title, String image, 
            String description, String author, int quantity, 
            StatusDTO status, CategoriesDTO category, float price, Date date) {
        this.bookID = bookID;
        this.title = title;
        this.image = image;
        this.description = description;
        this.author = author;
        this.quantity = quantity;
        this.status = status;
        this.category = category;
        this.price = price;
        this.date = date;
    }

    public BookDTO() {
    }

    @Override
    public String toString() {
        return "BookDTO{" + "bookID=" + bookID + ", title=" + title + ", image=" + image + ", description=" + description + ", author=" + author + ", status=" + status + ", category=" + category + ", quantity=" + quantity + ", price=" + price + ", date=" + date + '}';
    }

    
}
