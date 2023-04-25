<%@ page import="java.util.List" %>
<%@ page import="com.example.companyemployeeservlet.model.Company" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("country"); %>
<body>
<a href="/employees">Back</a>
<h2>Create Company</h2>
<form action="/createEmployee" method="post">
    <input type="text" name="name" placeholder="Name"><br>
    <input type="text" name="surname" placeholder="Surname"><br>
    <input type="email" name="email" placeholder="Email"><br>
    <select name="company_id">
         <% for (Company company : companies) {%>
        <option value="<%=company.getId()%>">  <%=company.getName()%></option>
           <% } %>
    </select>
    <input type="submit" value="create">
</form>
</body>
</html>
