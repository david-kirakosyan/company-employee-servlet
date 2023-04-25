package com.example.companyemployeeservlet.servlet;

import com.example.companyemployeeservlet.manager.CompanyManager;
import com.example.companyemployeeservlet.manager.EmployeeManager;
import com.example.companyemployeeservlet.model.Company;
import com.example.companyemployeeservlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

    private CompanyManager companyManager = new CompanyManager();
    private EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeManager.getById(id);
        List<Company> companyList = companyManager.getAll();
        req.setAttribute("country", companyList);
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("WEB-INF/updateEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        int companyId = Integer.parseInt(req.getParameter("company_id"));
        Company byId = companyManager.getById(companyId);
        Employee employee = new Employee(id,name,surname,email,byId);
        employeeManager.update(employee);
        resp.sendRedirect("/employees");
    }
}
