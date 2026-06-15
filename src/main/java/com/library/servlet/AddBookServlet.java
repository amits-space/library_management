package com.library.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.library.dao.BookDAO;
import com.library.model.Book;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String author = request.getParameter("author");

        Book book = new Book();
        book.setName(name);
        book.setGenre(genre);
        book.setAuthor(author);

        bookDAO.addBook(book);
        response.sendRedirect("issue.jsp");
    }
}