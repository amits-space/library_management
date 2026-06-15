<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.library.model.Book" %>
<%@ page import="com.library.dao.BookDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Issue Section</title>
</head>
<body>
    <h2>Library Management System</h2>
    <a href="issue.jsp"><b>Issue Section</b></a> | 
    <a href="return.jsp">Return Section</a> | 
    <a href="add_book.jsp">Add Book</a> | 
    <a href="remove_book.jsp">Remove Book</a>
    <hr>

    <h3>Issue Section</h3>
    
    <form action="issue.jsp" method="get">
        Search Book (ID or Name): 
        <input type="text" name="search" value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
        <input type="submit" value="Search">
    </form>
    <br>

    <table border="1" cellpadding="5">
        <tr>
            <th>Book ID</th>
            <th>Book Name</th>
            <th>Genre</th>
            <th>Author</th>
            <th>Action</th>
        </tr>
        <%
            BookDAO bookDAO = new BookDAO();
            String search = request.getParameter("search");
            List<Book> availableBooks = bookDAO.getAvailableBooks(search);
            if (availableBooks.isEmpty()) {
        %>
            <tr>
                <td colspan="5">No available books found.</td>
            </tr>
        <%
            } else {
                for (Book book : availableBooks) {
        %>
            <tr>
                <td><%= book.getFormattedId() %></td>
                <td><%= book.getName() %></td>
                <td><%= book.getGenre() %></td>
                <td><%= book.getAuthor() %></td>
                <td>
                    <form action="IssueBookServlet" method="post" onsubmit="return askUSN(this, '<%= book.getFormattedId() %>');">
                        <input type="hidden" name="bookId" value="<%= book.getId() %>">
                        <input type="hidden" name="usn" id="usn_<%= book.getId() %>">
                        <input type="submit" value="Issue">
                    </form>
                </td>
            </tr>
        <%
                }
            }
        %>
    </table>

    <script>
    function askUSN(form, bookId) {
        var usn = prompt("Enter the USN of the issuer for Book ID " + bookId + ":");
        if (usn == null || usn.trim() === "") {
            alert("USN cannot be empty.");
            return false; 
        }
        form.elements['usn'].value = usn;
        return true; 
    }
    </script>
</body>
</html> 