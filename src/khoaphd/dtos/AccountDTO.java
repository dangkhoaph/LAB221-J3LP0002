package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class AccountDTO implements Serializable {
    
    private String userID, fullname;

    public AccountDTO(String userID, String fullname) {
        this.userID = userID;
        this.fullname = fullname;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(userID);
        v.add(fullname);
        return v;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}