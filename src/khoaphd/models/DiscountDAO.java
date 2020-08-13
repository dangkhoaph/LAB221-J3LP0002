package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.DiscountDTO;

/**
 *
 * @author KhoaPHD
 */
public class DiscountDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final boolean ACTIVE = true;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public Vector<DiscountDTO> getCodesByUserID(String userID) throws Exception {
        Vector<DiscountDTO> result = new Vector<>();
        String discountID;
        double discountPercent;
        Date expiredDate;
        DiscountDTO dto;
        try {
            String sql = "SELECT DiscountID, DiscountPercent, ExpiredDate "
                    + "FROM Discount WHERE UserID = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                discountID = rs.getString("DiscountID");
                discountPercent = rs.getDouble("DiscountPercent");
                expiredDate = rs.getDate("ExpiredDate");
                dto = new DiscountDTO(discountID, userID, discountPercent, expiredDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public DiscountDTO getCodeByID(String discountID) throws Exception {
        double discountPercent;
        Date expiredDate;
        DiscountDTO dto = null;
        try {
            String sql = "SELECT DiscountPercent, ExpiredDate "
                    + "FROM Discount WHERE DiscountID = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discountID);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                discountPercent = rs.getDouble("DiscountPercent");
                expiredDate = rs.getDate("ExpiredDate");
                dto = new DiscountDTO(discountID, discountPercent, expiredDate);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean disableExpiredCodes() throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Discount SET Status = ? WHERE ExpiredDate < ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, !ACTIVE);
            preStm.setDate(2, new Date(System.currentTimeMillis()));
            preStm.setBoolean(3, ACTIVE);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(DiscountDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO Discount VALUES (?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getDiscountID());
            preStm.setString(2, dto.getUserID());
            preStm.setDouble(3, dto.getPercent());
            preStm.setDate(4, dto.getExpiredDate());
            preStm.setBoolean(5, ACTIVE);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean disable(String discountID) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Discount SET Status = ? WHERE DiscountID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, !ACTIVE);
            preStm.setString(2, discountID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}