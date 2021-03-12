package com.project.java_ee_lab2.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@WebFilter(filterName = "LogFilter")
public class LogFilter implements Filter {
  private List<String> registerRequestParameters;
  private List<String> registerCookieParameters;

  public void init(FilterConfig config) throws ServletException {
    registerRequestParameters = new ArrayList<String>();
    registerCookieParameters = new ArrayList<String>();
  }

  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
    System.out.println("1. LoggingFilter");
    Enumeration<String> requestParameterNames = request.getParameterNames();
    Cookie[] requestCookieNames = ((HttpServletRequest) request).getCookies();

    while (requestParameterNames.hasMoreElements()) {
      String key = requestParameterNames.nextElement();
      String str = key + "=" + request.getParameter(key) + " [" + new Date() + "]";
      registerRequestParameters.add(str);
      System.out.println("Add[registerRequestParameters]: " + str);
    }

    for (Cookie cookie : requestCookieNames) {
      String str = cookie.getName() + " = " + cookie.getValue() + " [" + new Date() + "]";
      registerCookieParameters.add(str);
      System.out.println("Add[registerCookieParameters]: " + str);
    }

    chain.doFilter(request, response);
  }
}
