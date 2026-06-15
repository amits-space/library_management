package com.library.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.library.dao.BookDAO;

@WebServlet("/IssueBookServlet")
public class IssueBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String usn = request.getParameter("usn");

        bookDAO.issueBook(bookId, usn);
        response.sendRedirect("issue.jsp");
    }
}