package com.example.companyemployeeservlet.servlet;

import com.example.companyemployeeservlet.manager.UserManager;
import com.example.companyemployeeservlet.model.User;
import com.example.companyemployeeservlet.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = userManager.getByEmail(email);
        if (user == null){
            userManager.save(User.builder()
                    .name(req.getParameter("name"))
                    .surname(req.getParameter("surname"))
                    .email(email)
                    .password(req.getParameter("password"))
                    .userType(UserType.valueOf(req.getParameter("type")))
                    .build());

        }
        resp.sendRedirect("/");
    }
}

