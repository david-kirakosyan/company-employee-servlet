<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    if (session.getAttribute("user") != null){
        response.sendRedirect("/home");
    }
    String msg = (String) session.getAttribute("msg");
%>
<% if (msg != null){%>
<span style="color: red"><%=msg%></span><br>
<%
session.removeAttribute("msg");
}%>
Login:
<form action="/login" method="post">
    <input name="email" type="email" placeholder="email"><br>
    <input name="password" type="password" placeholder="password"><br>
    <input type="submit" name="Login">
</form>
<a href="/register.jsp">Register</a>
</body>
</html>