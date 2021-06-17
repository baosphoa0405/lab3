/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baotpg.books;

/**
 *
 * @author Admin
 */
public class BookError {

    private String bookIDEmpty, bookIDDuplicate, bookIDFormat;
    private String titleEmpty, titleLength;
    private String imageEmpty, imageFormat;
    private String descriptionEmpty, descriptionLength;
    private String priceEmpty, priceFormat;
    private String quanityEmpty;
    private String authorEmpty, authorLength;

    public String getAuthorEmpty() {
        return authorEmpty;
    }

    public void setAuthorEmpty(String authorEmpty) {
        this.authorEmpty = authorEmpty;
    }

    public String getAuthorLength() {
        return authorLength;
    }

    public void setAuthorLength(String authorLength) {
        this.authorLength = authorLength;
    }

    public BookError(String bookIDEmpty, String bookIDDuplicate, String bookIDFormat, String titleEmpty, String titleLength, String imageEmpty, String imageFormat, String descriptionEmpty, String descriptionLength, String priceEmpty, String priceFormat, String quanityEmpty, String authorEmpty, String authorLength) {
        this.bookIDEmpty = bookIDEmpty;
        this.bookIDDuplicate = bookIDDuplicate;
        this.bookIDFormat = bookIDFormat;
        this.titleEmpty = titleEmpty;
        this.titleLength = titleLength;
        this.imageEmpty = imageEmpty;
        this.imageFormat = imageFormat;
        this.descriptionEmpty = descriptionEmpty;
        this.descriptionLength = descriptionLength;
        this.priceEmpty = priceEmpty;
        this.priceFormat = priceFormat;
        this.quanityEmpty = quanityEmpty;
        this.authorEmpty = authorEmpty;
        this.authorLength =authorLength;
    }

    @Override
    public String toString() {
        return "BookError{" + "bookIDEmpty=" + bookIDEmpty + ", bookIDDuplicate=" + bookIDDuplicate + ", bookIDFormat=" + bookIDFormat + ", titleEmpty=" + titleEmpty + ", titleLength=" + titleLength + ", imageEmpty=" + imageEmpty + ", imageFormat=" + imageFormat + ", descriptionEmpty=" + descriptionEmpty + ", descriptionLength=" + descriptionLength + ", priceEmpty=" + priceEmpty + ", priceFormat=" + priceFormat + ", quanityEmpty=" + quanityEmpty + ", authorEmpty=" + authorEmpty + ", authorLength=" + authorLength + '}';
    }

    public String getBookIDEmpty() {
        return bookIDEmpty;
    }

    public void setBookIDEmpty(String bookIDEmpty) {
        this.bookIDEmpty = bookIDEmpty;
    }

    public String getBookIDDuplicate() {
        return bookIDDuplicate;
    }

    public void setBookIDDuplicate(String bookIDDuplicate) {
        this.bookIDDuplicate = bookIDDuplicate;
    }

    public String getBookIDFormat() {
        return bookIDFormat;
    }

    public void setBookIDFormat(String bookIDFormat) {
        this.bookIDFormat = bookIDFormat;
    }

    public String getTitleEmpty() {
        return titleEmpty;
    }

    public void setTitleEmpty(String titleEmpty) {
        this.titleEmpty = titleEmpty;
    }

    public String getTitleLength() {
        return titleLength;
    }

    public void setTitleLength(String titleLength) {
        this.titleLength = titleLength;
    }

    public String getImageEmpty() {
        return imageEmpty;
    }

    public void setImageEmpty(String imageEmpty) {
        this.imageEmpty = imageEmpty;
    }

    public String getImageFormat() {
        return imageFormat;
    }

    public void setImageFormat(String imageFormat) {
        this.imageFormat = imageFormat;
    }

    public String getDescriptionEmpty() {
        return descriptionEmpty;
    }

    public void setDescriptionEmpty(String descriptionEmpty) {
        this.descriptionEmpty = descriptionEmpty;
    }

    public String getDescriptionLength() {
        return descriptionLength;
    }

    public void setDescriptionLength(String descriptionLength) {
        this.descriptionLength = descriptionLength;
    }

    public String getPriceEmpty() {
        return priceEmpty;
    }

    public void setPriceEmpty(String priceEmpty) {
        this.priceEmpty = priceEmpty;
    }

    public String getPriceFormat() {
        return priceFormat;
    }

    public void setPriceFormat(String priceFormat) {
        this.priceFormat = priceFormat;
    }

    public String getQuanityEmpty() {
        return quanityEmpty;
    }

    public void setQuanityEmpty(String quanityEmpty) {
        this.quanityEmpty = quanityEmpty;
    }

}
