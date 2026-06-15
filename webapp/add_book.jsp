<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
    <h2>Library Management System</h2>
    <a href="issue.jsp">Issue Section</a> | 
    <a href="return.jsp">Return Section</a> | 
    <a href="add_book.jsp"><b>Add Book</b></a> | 
    <a href="remove_book.jsp">Remove Book</a>
    <hr>

    <h3>Add New Book</h3>
    <form action="AddBookServlet" method="post">
        <table cellpadding="5">
            <tr>
                <td>Book Name:</td>
                <td><input type="text" name="name" required></td>
            </tr>
            <tr>
                <td>Genre:</td>
                <td><input type="text" name="genre" required></td>
            </tr>
            <tr>
                <td>Author:</td>
                <td><input type="text" name="author" required></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Add Book"></td>
            </tr>
        </table>
    </form>
</body>
</html>