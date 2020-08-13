package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.BillDTO;

/**
 *
 * @author KhoaPHD
 */
public class BillDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public int generateNewID() throws Exception {
        int id = 1;
        try {
            String sql = "SELECT TOP 1 BillID FROM Bill ORDER BY BillID DESC";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("BillID") + 1;
            }
        } finally {
            closeConnection();
        }
        return id;
    }
    
    public boolean insert(BillDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO Bill VALUES (?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getBillID());
            preStm.setString(2, dto.getUserID());
            preStm.setDouble(3, dto.getTotalAll());
            preStm.setDate(4, dto.getBuyDate());
            preStm.setString(5, dto.getDiscountID());
            preStm.setDouble(6, dto.getTotalDiscount());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<BillDTO> getBillsByUser(String userID) throws Exception {
        Vector<BillDTO> result = new Vector<>();
        int billID;
        Date buyDate;
        BillDTO dto;
        try {
            String sql = "SELECT BillID, BuyDate FROM Bill WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                billID = rs.getInt("BillID");
                buyDate = rs.getDate("BuyDate");
                dto = new BillDTO(billID, buyDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public BillDTO getBillByID(int billID) throws Exception {
        BillDTO dto = null;
        try {
            String sql = "SELECT Total, DiscountID, TotalAfterDiscount "
                    + "FROM Bill WHERE BillID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, billID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                double total = rs.getDouble("Total");
                String discountID = rs.getString("DiscountID") == null ? "None" : rs.getString("DiscountID");
                double totalDiscount = rs.getDouble("TotalAfterDiscount");
                dto = new BillDTO(billID, total, discountID, totalDiscount);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public Vector<BillDTO> getBillsByBuyDate(String userID, Date buyDate) throws Exception {
        Vector<BillDTO> result = new Vector<>();
        int billID;
        BillDTO dto;
        try {
            String sql = "SELECT BillID FROM Bill WHERE UserID = ? AND BuyDate = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            preStm.setDate(2, buyDate);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                billID = rs.getInt("BillID");
                dto = new BillDTO(billID, buyDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}