package com.kostenko.servlets.filters;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import com.kostenko.validators.impl.ValidationServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientFilter implements Filter{

    private ValidationService validationService;

    public ClientFilter(ValidationService validationService) {
        this.validationService = validationService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();

        String clientId = servletRequest.getParameter("clientId");
        if (clientId != null && clientId.isEmpty()) {
            servletRequest.setAttribute("message", "Empty client id.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        String age = servletRequest.getParameter("age");
        if (age != null && age.isEmpty()) {
            servletRequest.setAttribute("message", "Empty age.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        if ("POST".equals(method)) {

            String parameterMethod = servletRequest.getParameter("method");
            if ("put".equals(parameterMethod) || "delete".equals(parameterMethod)) {

                try {
                    validationService.validateClientExists(Long.parseLong(clientId));
                } catch (BusinessException e) {
                    servletRequest.setAttribute("message", e.getMessage());
                    servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
                    return;
                }
            }

            if ("put".equals(parameterMethod) || parameterMethod == null) {

                String email = servletRequest.getParameter("email");
                String phone = servletRequest.getParameter("phone");
                try {
                    validationService.validateClientField(Integer.parseInt(age), email, phone);
                } catch(NumberFormatException|BusinessException e) {
                    servletRequest.setAttribute("message", e.getMessage());
                    servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
                    return;
                }
            }
        }

         filterChain.doFilter(servletRequest, servletResponse);
    }
}
