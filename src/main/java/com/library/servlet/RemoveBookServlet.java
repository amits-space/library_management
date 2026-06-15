package com.library.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.library.dao.BookDAO;

@WebServlet("/RemoveBookServlet")
public class RemoveBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdStr = request.getParameter("bookId");
        if (bookIdStr != null) {
            int bookId = Integer.parseInt(bookIdStr);
            bookDAO.deleteBook(bookId);
        }
        response.sendRedirect("remove_book.jsp");
    }
}