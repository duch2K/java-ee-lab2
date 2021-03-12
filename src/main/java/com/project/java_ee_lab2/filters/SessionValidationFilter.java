package com.project.java_ee_lab2.filters;

import com.project.java_ee_lab2.UserList;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;


@WebFilter(filterName = "SessionValidationFilter")
public class SessionValidationFilter implements Filter {
  FilterConfig config;
  UserList userList;

  public void init(FilterConfig config) throws ServletException {
    this.config = config;
    this.userList = new UserList();
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    System.out.println("2. SessionValidationFilter");
    HttpSession session = ((HttpServletRequest) request).getSession();
    Cookie[] cookies = ((HttpServletRequest) request).getCookies();

    if (session.getAttribute("AUTH_STATUS") != null) {
//            String key = (String) session.getAttribute("AUTH_STATUS");
//            User user = userList.getUser(key);
//
//            response.addCookie(new Cookie("username", user.getUsername()));
//            response.addCookie(new Cookie("password", user.getPassword()));
      chain.doFilter(request, response);
    }

    String username = null;
    String password = null;
    for (Cookie cookie : cookies) {
      if (cookie.getName().equals("AUTH_STATUS")) {
        session.setAttribute("AUTH_STATUS", "TRUE");
        chain.doFilter(request, response);
      }
      if (cookie.getName().equals("username")) {
        username = cookie.getValue();
      }
      if (cookie.getName().equals("password")) {
        password = cookie.getValue();
      }
    }

    if (username != null && password != null) {
      Enumeration<String> elements = config.getInitParameterNames();

      while (elements.hasMoreElements()) {
        String elemName = elements.nextElement();

        if (elemName.equals(username) && config.getInitParameter(elemName).equals(password)) {
//                    session.setAttribute("AUTH_STATUS", "TRUE");
          System.out.println("checks pass username");
          chain.doFilter(request, response);
        }
      }
    }


    ((HttpServletResponse) response).sendRedirect("login");
  }
}
