package com.project.java_ee_lab2.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ResponseFilter")
public class ResponseFilter implements Filter {
  public void init(FilterConfig config) throws ServletException {
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    System.out.println("3. ResponseFilter");

    chain.doFilter(request, response);

    HttpServletResponse servletResponse = ((HttpServletResponse) response);


    Cookie[] cookies = ((HttpServletRequest) request).getCookies();

    for (Cookie cookie : cookies) {
      cookie.setMaxAge(cookie.getMaxAge() + (30 * 60));
      servletResponse.addCookie(cookie);
    }

    HttpSession session = ((HttpServletRequest) request).getSession();
    session.setMaxInactiveInterval(session.getMaxInactiveInterval() + (30 * 60));
  }
}
