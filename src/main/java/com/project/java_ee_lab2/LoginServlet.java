package com.project.java_ee_lab2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/action-login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<h2>Hello " + name + ", nice to see you!</h2>");
        out.println("<span>Tsss, your password is:  " + password + "</span>");
        out.println("</html>");
    }
}