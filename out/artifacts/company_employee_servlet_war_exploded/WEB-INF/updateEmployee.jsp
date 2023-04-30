<%@ page import="com.example.companyemployeeservlet.model.Company" %>
<%@ page import="com.example.companyemployeeservlet.model.Employee" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% Employee employee = (Employee) request.getAttribute("employee");%>
<% List<Company> companies = (List<Company>) request.getAttribute("country"); %>
<body>
<a href="/employees">Back</a>
<h2>Update Employee</h2>
<form action="/updateEmployee" method="post">
    <input name="id" type="hidden" value="<%=employee.getId()%>">
    <input type="text" name="name" placeholder="Name" value="<%=employee.getName()%>"><br>
    <input type="text" name="surname" placeholder="Surname" value="<%=employee.getSurname()%>"><br>
    <input type="email" name="email" placeholder="Email" value="<%=employee.getEmail()%>"><br>
    <select name="company_id">
        <% for (Company company : companies) {%>
        <option value="<%=company.getId()%>">  <%=company.getName()%></option>
        <% } %>
    </select>
    <input type="submit" value="update">
</form>
</body>
</html>
