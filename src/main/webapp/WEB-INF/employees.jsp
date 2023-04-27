<%@ page import="java.util.List" %>
<%@ page import="com.example.companyemployeeservlet.model.Employee" %>
<%@ page import="com.example.companyemployeeservlet.model.User" %>
<%@ page import="com.example.companyemployeeservlet.model.UserType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    User user = (User) session.getAttribute("user");
%>
<body>
<a href="/">Back</a>
<h2>Employees</h2> <a href="/createEmployee">Create Employee</a>
<table>
    <tr>
    <th>id</th>
    <th>name</th>
    <th>surname</th>
    <th>email</th>
    <th>Company name</th>
        <% if (user.getUserType() == UserType.ADMIN){%>
    <th>action</th>
        <%}%>
    </tr>
    <% if (employees != null && !employees.isEmpty()) {%>
    <% for (Employee employee : employees) { %>
     <tr>
         <td><%=employee.getId()%></td>
         <td><%=employee.getName()%></td>
         <td><%=employee.getSurname()%></td>
         <td><%=employee.getEmail()%></td>
         <td><%=employee.getCompany().getName()%></td>
         <% if (user.getUserType() == UserType.ADMIN){%>
         <td><a href="/removeEmployee?id=<%=employee.getId()%>">Delete</a> |
             <a href="/updateEmployee?id=<%=employee.getId()%>">Update</a></td>
         <% } %>
     </tr>
   <% } %>
   <% } %>
</table>
</body>
</html>
