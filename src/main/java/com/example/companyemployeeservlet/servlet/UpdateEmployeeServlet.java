package com.example.companyemployeeservlet.servlet;

import com.example.companyemployeeservlet.constants.SharedConstants;
import com.example.companyemployeeservlet.manager.CompanyManager;
import com.example.companyemployeeservlet.manager.EmployeeManager;
import com.example.companyemployeeservlet.model.Company;
import com.example.companyemployeeservlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateEmployee")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, // 5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1)
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
        int companyId = Integer.parseInt(req.getParameter("company_id"));
        Company byId = companyManager.getById(companyId);
        Part profilePicPart = req.getPart("profilePic");
        String picName = null;
        if (profilePicPart != null && profilePicPart.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePicPart.getSubmittedFileName();
            profilePicPart.write(SharedConstants.UPLOAD_FOLDER + picName);
        }
        Employee employee = Employee.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .picName(picName)
                .company(byId)
                .build();
        employeeManager.update(employee);
        resp.sendRedirect("/employees");
    }
}
