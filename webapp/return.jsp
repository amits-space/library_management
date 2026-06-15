<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.dao.BookDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Return Section</title>
</head>
<body>
    <%
        // Triggers the exact requested return pop-up alert if values are present in redirect
        String returned = request.getParameter("returned");
        if ("true".equals(returned)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
    %>
        <script>
            alert("Book ID: <%= id %>\nBook Name: <%= name %>\nwas returned");
        </script>
    <%
        }
    %>

    <h2>Library Management System</h2>
    <a href="issue.jsp">Issue Section</a> | 
    <a href="return.jsp"><b>Return Section</b></a> | 
    <a href="add_book.jsp">Add Book</a> | 
    <a href="remove_book.jsp">Remove Book</a>
    <hr>

    <h3>Return Section</h3>

    <table border="1" cellpadding="5">
        <tr>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Genre</th>
            <th>Author</th>
            <th>Issued To (USN)</th>
            <th>Action</th>
        </tr>
        <%
            BookDAO bookDAO = new BookDAO();
            List<Book> issuedBooks = bookDAO.getIssuedBooks();
            if (issuedBooks.isEmpty()) {
        %>
            <tr>
                <td colspan="6">No issued books right now.</td>
            </tr>
        <%
            } else {
                for (Book book : issuedBooks) {
        %>
            <tr>
                <td><%= book.getFormattedId() %></td>
                <td><%= book.getName() %></td>
                <td><%= book.getGenre() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getIssuedTo() %></td>
                <td>
                    <form action="ReturnBookServlet" method="post">
                        <input type="hidden" name="bookId" value="<%= book.getId() %>">
                        <input type="submit" value="Return">
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>