package com.library.model;

public class Book {
    private int id;
    private String name;
    private String genre;
    private String author;
    private String status; // 'AVAILABLE' or 'ISSUED'
    private String issuedTo; // Stores USN

    public Book() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getIssuedTo() { return issuedTo; }
    public void setIssuedTo(String issuedTo) { this.issuedTo = issuedTo; }

    // Formats integer id to 001, 002, etc.
    public String getFormattedId() {
        return String.format("%03d", id);
    }
}