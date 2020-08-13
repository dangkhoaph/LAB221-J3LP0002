package khoaphd.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class BookDTO implements Serializable {
    
    private String bookID, title, author, category, description, imageName;
    private double price;
    private int quantity;
    private Date importDate;

    public BookDTO(String bookID, String title, String author, double price) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.price = price;
    }
    
    public BookDTO(String bookID, String title, String author, String category,
            double price, int quantity) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public BookDTO(String bookID, String title, String author, String category,
            String description, String imageName, double price, int quantity,
            Date importDate) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.description = description;
        this.imageName = imageName;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;
    }
    
    public Vector toVectorLong() {
        Vector v = new Vector();
        v.add(bookID);
        v.add(title);
        v.add(author);
        v.add(category);
        v.add(price);
        v.add(quantity);
        return v;
    }
    
    public Vector toVectorShort() {
        Vector v = new Vector();
        v.add(bookID);
        v.add(title);
        v.add(author);
        return v;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }
}