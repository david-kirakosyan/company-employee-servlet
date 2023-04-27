<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    if (session.getAttribute("user") != null){
        response.sendRedirect("/home");
    }
%>
Register:
<form action="/register" method="post">
    <input name="name" type="text" placeholder="name"><br>
    <input name="surname" type="text" placeholder="surname"><br>
    <input name="email" type="email" placeholder="email"><br>
    <input name="password" type="password" placeholder="password"><br>
    <select name="type">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
    </select><br>
    <input type="submit" name="Register">
</form>
<a href="/">Login</a>
</body>
</html>
