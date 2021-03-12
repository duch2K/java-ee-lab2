package com.project.java_ee_lab2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;


@WebServlet(name = "Register", value = "/action-register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html>");
//        out.println("<h2>Welcome " + name + "! Your email: " + email + "</h2>");
//        out.println("<span>Tsss, your password is:  " + password + "</span>");
//        out.println("</html>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = "registration";

        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("status", status);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
