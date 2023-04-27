<%@ page import="com.example.companyemployeeservlet.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
Welcome <%=user.getName()%> <%=user.getSurname()%> <a href="/logout">logout</a><br>
<a href="/companies">Companies</a> |
<a href="/employees">Employees</a>
</body>
</html>
