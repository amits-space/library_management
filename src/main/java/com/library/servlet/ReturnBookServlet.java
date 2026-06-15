package com.library.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.library.dao.BookDAO;
import com.library.model.Book;

@WebServlet("/ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        
        Book book = bookDAO.getBookById(bookId);
        if (book != null) {
            bookDAO.returnBook(bookId);
            // Redirect back with parameters to trigger the popup
            response.sendRedirect("return.jsp?returned=true&id=" + book.getFormattedId() + "&name=" + URLEncoder.encode(book.getName(), "UTF-8"));
        } else {
            response.sendRedirect("return.jsp");
        }
    }
}