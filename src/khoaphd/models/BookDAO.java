package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.BookDTO;

/**
 *
 * @author KhoaPHD
 */
public class BookDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final boolean ACTIVE = true;
    private static final boolean INACTIVE = false;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public Vector<BookDTO> getAllBooksForAdmin() throws Exception {
        Vector<BookDTO> result = new Vector<>();
        String id = null;
        String title = null;
        String author = null;
        String category = null;
        double price = 0.0;
        int quantity = 0;
        BookDTO dto = null;
        try {
            String sql = "SELECT BookID, Title, Author, Category, Price, Quantity "
                    + "FROM Book WHERE Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("BookID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                category = rs.getString("Category");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                dto = new BookDTO(id, title, author, category, price, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<BookDTO> getAllBooksForUser() throws Exception {
        Vector<BookDTO> result = new Vector<>();
        String id = null;
        String title = null;
        String author = null;
        double price = 0.0;
        BookDTO dto = null;
        try {
            String sql = "SELECT BookID, Title, Author, Price "
                    + "FROM Book WHERE Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("BookID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                price = rs.getDouble("Price");
                dto = new BookDTO(id, title, author, price);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public BookDTO getBookByID(String id) throws Exception {
        String title = null;
        String author = null;
        String category = null;
        String description = null;
        String imageName = null;
        double price = 0.0;
        int quantity = 0;
        Date importDate = null;
        BookDTO dto = null;
        try {
            String sql = "SELECT Title, Author, Category, Description, ImageName, Price, Quantity, ImportDate "
                    + "FROM Book WHERE BookID = ? AND Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            if (rs.next()) {
                title = rs.getString("Title");
                author = rs.getString("Author");
                category = rs.getString("Category");
                description = rs.getString("Description");
                imageName = rs.getString("ImageName");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                importDate = rs.getDate("ImportDate");
                dto = new BookDTO(id, title, author, category, description, imageName, price, quantity, importDate);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public Vector<BookDTO> getBooksByLikeTitle(String searchTitle) throws Exception {
        Vector<BookDTO> result = new Vector<>();
        String id = null;
        String title = null;
        String author = null;
        String category = null;
        double price = 0.0;
        int quantity = 0;
        BookDTO dto = null;
        try {
            String sql = "SELECT BookID, Title, Author, Category, Price, Quantity "
                    + "FROM Book WHERE Title LIKE ? AND Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchTitle + "%");
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("BookID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                category = rs.getString("Category");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                dto = new BookDTO(id, title, author, category, price, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<BookDTO> getBooksByLikeCategory(String searchCategory) throws Exception {
        Vector<BookDTO> result = new Vector<>();
        String id = null;
        String title = null;
        String author = null;
        String category = null;
        double price = 0.0;
        int quantity = 0;
        BookDTO dto = null;
        try {
            String sql = "SELECT BookID, Title, Author, Category, Price, Quantity "
                    + "FROM Book WHERE Category LIKE ? AND Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + searchCategory + "%");
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("BookID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                category = rs.getString("Category");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                dto = new BookDTO(id, title, author, category, price, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<BookDTO> getBooksByPriceRange(double from, double to) throws Exception {
        Vector<BookDTO> result = new Vector<>();
        String id = null;
        String title = null;
        String author = null;
        String category = null;
        double price = 0.0;
        int quantity = 0;
        BookDTO dto = null;
        try {
            String sql = "SELECT BookID, Title, Author, Category, Price, Quantity "
                    + "FROM Book WHERE Price >= ? AND Price <= ? AND Quantity > 0 AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setDouble(1, from);
            preStm.setDouble(2, to);
            preStm.setBoolean(3, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("BookID");
                title = rs.getString("Title");
                author = rs.getString("Author");
                category = rs.getString("Category");
                price = rs.getDouble("Price");
                quantity = rs.getInt("Quantity");
                dto = new BookDTO(id, title, author, category, price, quantity);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(BookDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO Book VALUES (?,?,?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getBookID());
            preStm.setString(2, dto.getTitle());
            preStm.setString(3, dto.getAuthor());
            preStm.setString(4, dto.getCategory());
            preStm.setString(5, dto.getDescription());
            preStm.setString(6, dto.getImageName());
            preStm.setDouble(7, dto.getPrice());
            preStm.setInt(8, dto.getQuantity());
            preStm.setDate(9, dto.getImportDate());
            preStm.setBoolean(10, ACTIVE);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean update(BookDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Book "
                    + "SET Title = ?, Author = ?, Category = ?, Description = ?, "
                    + "ImageName = ?, Price = ?, Quantity = ?, ImportDate = ? "
                    + "WHERE BookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getTitle());
            preStm.setString(2, dto.getAuthor());
            preStm.setString(3, dto.getCategory());
            preStm.setString(4, dto.getDescription());
            preStm.setString(5, dto.getImageName());
            preStm.setDouble(6, dto.getPrice());
            preStm.setInt(7, dto.getQuantity());
            preStm.setDate(8, dto.getImportDate());
            preStm.setString(9, dto.getBookID());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean delete(String id) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Book SET Status = ? WHERE BookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, INACTIVE);
            preStm.setString(2, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean isInStock(String bookID, int quantity) throws Exception {
        boolean result = false;
        try {
            String sql = "SELECT BookID FROM Book WHERE BookID = ? AND Quantity >= ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, bookID);
            preStm.setInt(2, quantity);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean updateStock(String bookID, int quantity) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Book SET Quantity = Quantity - ? WHERE BookID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setString(2, bookID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}