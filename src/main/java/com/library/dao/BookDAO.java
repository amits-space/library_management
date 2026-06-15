package com.library.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.library.model.Book;

public class BookDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true";
    private String jdbcUsername = "root"; 
    private String jdbcPassword = "root"; // Change this to your database password

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (name, genre, author, status) VALUES (?, ?, ?, 'AVAILABLE')";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getGenre());
            pstmt.setString(3, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAvailableBooks(String searchQuery) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'AVAILABLE'";
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            sql += " AND (id LIKE ? OR name LIKE ?)";
        }
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                pstmt.setString(1, "%" + searchQuery + "%");
                pstmt.setString(2, "%" + searchQuery + "%");
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getString("status"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getIssuedBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE status = 'ISSUED'";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setIssuedTo(rs.getString("issued_to"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                book.setGenre(rs.getString("genre"));
                book.setAuthor(rs.getString("author"));
                book.setStatus(rs.getString("status"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getBookById(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setName(rs.getString("name"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void issueBook(int id, String usn) {
        String sql = "UPDATE books SET status = 'ISSUED', issued_to = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usn);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int id) {
        String sql = "UPDATE books SET status = 'AVAILABLE', issued_to = NULL WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}