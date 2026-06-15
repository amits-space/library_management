<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.dao.BookDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Remove Book</title>
</head>
<body>
    <h2>Library Management System</h2>
    <a href="issue.jsp">Issue Section</a> | 
    <a href="return.jsp">Return Section</a> | 
    <a href="add_book.jsp">Add Book</a> | 
    <a href="remove_book.jsp"><b>Remove Book</b></a>
    <hr>

    <h3>Remove Book from Database</h3>
    
    <form action="RemoveBookServlet" method="post">
        <table border="1" cellpadding="5">
            <tr>
                <th>Select</th>
                <th>Book ID</th>
                <th>Book Name</th>
                <th>Genre</th>
                <th>Author</th>
                <th>Status</th>
            </tr>
            <%
                BookDAO bookDAO = new BookDAO();
                List<Book> allBooks = bookDAO.getAllBooks();
                if (allBooks.isEmpty()) {
                %>
                    <tr>
                        <td colspan="6">No books found in the database.</td>
                    </tr>
                <%
                } else {
                    for (Book book : allBooks) {
                %>
                    <tr>
                        <td><input type="radio" name="bookId" value="<%= book.getId() %>" required></td>
                        <td><%= book.getFormattedId() %></td>
                        <td><%= book.getName() %></td>
                        <td><%= book.getGenre() %></td>
                        <td><%= book.getAuthor() %></td>
                        <td><%= book.getStatus() %></td>
                    </tr>
                <%
                    }
                }
            %>
        </table>
        <br>
        <% if (!allBooks.isEmpty()) { %>
            <input type="submit" value="Remove Selected Book" onclick="return confirm('Are you sure you want to delete this book permanently?');">
        <% } %>
    </form>
</body>
</html>