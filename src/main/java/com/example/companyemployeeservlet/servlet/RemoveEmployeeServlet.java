package com.example.companyemployeeservlet.servlet;

import com.example.companyemployeeservlet.manager.EmployeeManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        employeeManager.removeById(id);
        resp.sendRedirect("/employees");
    }
}
