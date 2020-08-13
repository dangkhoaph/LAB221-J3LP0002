package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.BookInBillDTO;

/**
 *
 * @author KhoaPHD
 */
public class BookInBillDAO implements Serializable {
    
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
    
    public boolean insert(BookInBillDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO BookInBill VALUES (?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getBillID());
            preStm.setString(2, dto.getBookID());
            preStm.setDouble(3, dto.getPrice());
            preStm.setInt(4, dto.getQuantity());
            preStm.setDouble(5, dto.getTotal());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<BookInBillDTO> getBooksByBill(int billID) throws Exception {
        Vector<BookInBillDTO> result = new Vector<>();
        String bookID;
        double price, total;
        int quantity;
        BookInBillDTO dto;
        try {
            String sql = "SELECT BookID, Price, Quantity, Total "
                    + "FROM BookInBill WHERE BillID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, billID);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                bookID = rs.getString("BookID");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                total = rs.getDouble("Total");
                dto = new BookInBillDTO(billID, bookID, price, quantity, total);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}