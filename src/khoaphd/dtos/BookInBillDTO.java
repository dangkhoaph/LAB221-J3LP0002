package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class BookInBillDTO implements Serializable {
    
    private int billID;
    private String bookID;
    private double price;
    private int quantity;
    private double total;

    public BookInBillDTO(int billID, String bookID, double price, int quantity, double total) {
        this.billID = billID;
        this.bookID = bookID;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(bookID);
        v.add(price);
        v.add(quantity);
        v.add(total);
        return v;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}