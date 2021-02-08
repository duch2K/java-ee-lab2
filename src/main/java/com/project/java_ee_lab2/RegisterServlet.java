package com.project.java_ee_lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Register", value = "/action-register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<h2>Welcome " + name + "! Your email: " + email + "</h2>");
        out.println("<span>Tsss, your password is:  " + password + "</span>");
        out.println("</html>");
    }
}
