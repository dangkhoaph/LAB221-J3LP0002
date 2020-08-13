package khoaphd.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class DiscountDTO implements Serializable {
    
    private String discountID, userID;
    private double percent;
    private Date expiredDate;

    public DiscountDTO(String discountID, String userID, double percent, Date expiredDate) {
        this.discountID = discountID;
        this.userID = userID;
        this.percent = percent;
        this.expiredDate = expiredDate;
    }

    public DiscountDTO(String discountID, double percent, Date expiredDate) {
        this.discountID = discountID;
        this.percent = percent;
        this.expiredDate = expiredDate;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(discountID);
        v.add(percent);
        v.add(expiredDate);
        return v;
    }

    public String getDiscountID() {
        return discountID;
    }

    public void setDiscountID(String discountID) {
        this.discountID = discountID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
}