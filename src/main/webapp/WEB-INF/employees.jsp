<%@ page import="java.util.List" %>
<%@ page import="com.example.companyemployeeservlet.model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<% List<Employee> employees = (List<Employee>) request.getAttribute("employees");%>
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
    <th>action</th>
    </tr>
    <% if (employees != null && !employees.isEmpty()) {%>
    <% for (Employee employee : employees) { %>
     <tr>
         <td><%=employee.getId()%></td>
         <td><%=employee.getName()%></td>
         <td><%=employee.getSurname()%></td>
         <td><%=employee.getEmail()%></td>
         <td><%=employee.getCompany().getName()%></td>
         <td><a href="/removeEmployee?id=<%=employee.getId()%>">Delete</a> |
             <a href="/updateEmployee?id=<%=employee.getId()%>">Update</a></td>
     </tr>
   <% } %>
   <% } %>
</table>
</body>
</html>
