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

    String msg = (String) request.getAttribute("msg");
%>
<% if (msg != null){%>
<samp style="color: red"><%=msg%></samp>
<%}%>
Register:
<form action="/register" method="post">
    <input name="name" type="text" placeholder="name" required><br>
    <input name="surname" type="text" placeholder="surname" required><br>
    <input name="email" type="email" placeholder="email" required><br>
    <input name="password" type="password" placeholder="password" required><br>
    <select name="type">
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
    </select><br>
    <input type="submit" name="Register">
</form>
<a href="/">Login</a>
</body>
</html>
