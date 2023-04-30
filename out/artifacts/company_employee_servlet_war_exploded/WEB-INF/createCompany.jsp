<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Company</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<a href="/companies">Back</a>
<h2>Create Company</h2>
<form action="/createCompany" method="post">
    <input type="text" name="name" placeholder="Name"><br>
    <input type="text" name="country" placeholder="Country"><br>
    <input type="submit" value="create">
</form>
</body>
</html>
