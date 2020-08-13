package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.AccountDTO;

/**
 *
 * @author KhoaPHD
 */
public class AccountDAO implements Serializable {
    
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
    
    public String checkLogin(String id, String password) throws Exception {
        String role = "Failed";
        try {
            String sql = "SELECT Role FROM Account WHERE UserID = ? AND Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
    
    public Vector<AccountDTO> getAllUserRole() throws Exception {
        Vector<AccountDTO> result = new Vector<>();
        String id = null;
        String fullname = null;
        AccountDTO dto = null;
        try {
            String sql = "SELECT UserID, Fullname FROM Account WHERE Role = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "User");
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                fullname = rs.getString("Fullname");
                dto = new AccountDTO(id, fullname);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}