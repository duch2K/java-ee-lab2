package com.project.java_ee_lab2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.annotation.*;

@WebServlet(name = "Login", value = "/action-login")
public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        String password = request.getParameter("password");
//
//        PrintWriter out = response.getWriter();
//        out.println("<html>");
//        out.println("<h2>Hello " + name + ", nice to see you!</h2>");
//        out.println("<span>Tsss, your password is:  " + password + "</span>");
//        out.println("</html>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = "false";

        ServletConfig config = getServletConfig();
        Enumeration<String> elements = config.getInitParameterNames();

        while (elements.hasMoreElements()) {
            String elementUsername = elements.nextElement();
            String elementPassword = config.getInitParameter(elementUsername);

            if (elementUsername.equals(username) && elementPassword.equals(password)) {
                status = "true";
                break;
            }
        }

        request.setAttribute("status", status);

        if (status.equals("true")) {
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            HttpSession session = request.getSession();
            session.setAttribute("AUTH_STATUS", username);

            Cookie usernameCookie = new Cookie("username", username);
            usernameCookie.setMaxAge(30 * 60);

            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(30 * 60);

            Cookie authStatusCookie = new Cookie("AUTH_STATUS", username);
            usernameCookie.setMaxAge(30 * 60);

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
            response.addCookie(authStatusCookie);

            try {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (ServletException ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } catch (ServletException ex) {
                ex.printStackTrace();
            }
        }
    }
}
