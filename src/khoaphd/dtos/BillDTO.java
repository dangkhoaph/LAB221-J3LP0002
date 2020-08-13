package khoaphd.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class BillDTO implements Serializable {
    
    private int billID;
    private String userID;
    private double totalAll;
    private Date buyDate;
    private String discountID;
    private double totalDiscount;

    public BillDTO(int billID, Date buyDate) {
        this.billID = billID;
        this.buyDate = buyDate;
    }

    public BillDTO(int billID, double totalAll, String discountID, double totalDiscount) {
        this.billID = billID;
        this.totalAll = totalAll;
        this.discountID = discountID;
        this.totalDiscount = totalDiscount;
    }
    
    public BillDTO(int billID, String userID, double totalAll, Date buyDate, String discountID, double totalDiscount) {
        this.billID = billID;
        this.userID = userID;
        this.totalAll = totalAll;
        this.buyDate = buyDate;
        this.discountID = discountID;
        this.totalDiscount = totalDiscount;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(billID);
        v.add(buyDate);
        return v;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getTotalAll() {
        return totalAll;
    }

    public void setTotalAll(double totalAll) {
        this.totalAll = totalAll;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}