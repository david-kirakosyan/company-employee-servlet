package com.example.companyemployeeservlet.servlet;

import com.example.companyemployeeservlet.constants.SharedConstants;
import com.example.companyemployeeservlet.manager.EmployeeManager;
import com.example.companyemployeeservlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet("/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee byId = employeeManager.getById(id);
        if (byId != null){
            if (byId.getPicName() != null
                    || !byId.getPicName().equalsIgnoreCase("null")) {
                File file = new File(SharedConstants.UPLOAD_FOLDER + byId.getPicName());
                if (file.exists()) {
                    file.delete();
                }
            }
        }
        employeeManager.removeById(id);
        resp.sendRedirect("/employees");
    }
}
