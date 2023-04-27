<%@ page import="java.util.List" %>
<%@ page import="com.example.companyemployeeservlet.model.Company" %>
<%@ page import="com.example.companyemployeeservlet.model.User" %>
<%@ page import="com.example.companyemployeeservlet.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Companies</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% List<Company> companies = (List<Company>) request.getAttribute("companies");
    User user = (User) session.getAttribute("user");
%>
<body>
<a href="/">Back</a>
<h2>Companies</h2> <a href="/createCompany">Create Company</a>
<table>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>country</th>
        <% if (user.getUserType() == UserType.ADMIN){%>
        <th>action</th>
        <%}%>
    </tr>
    <% if (companies != null && !companies.isEmpty()) {%>
    <% for (Company company : companies) { %>
    <tr>
        <td><%=company.getId()%>
        </td>
        <td><%=company.getName()%>
        </td>
        <td><%=company.getCountry()%>
        </td>
        <% if (user.getUserType() == UserType.ADMIN){%>
        <td><a href="/removeCompany?id=<%=company.getId()%>">Delete</a> |
            <a href="/updateCompany?id=<%=company.getId()%>">Update</a></td>
        <% } %>
    </tr>
    <% } %>
    <% } %>
</table>
</body>
</html>
