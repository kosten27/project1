package com.kostenko.controllers.filters;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.dao.impl.ClientDaoImpl;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import com.kostenko.validators.impl.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ClientFilter implements Filter {

    public static final String MESSAGE_PAGE = "message";
    public static final String MESSAGE_ATTRIBUTE = "message";
    private ValidationService validationService;

    public ClientFilter() {
        DataSourceDB dataSource = new DataSourceDB();
        ClientDao clientDao = new ClientDBDao(dataSource);
        this.validationService = new ValidationServiceImpl(clientDao);
    }
//
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String method = httpServletRequest.getMethod();

        String clientId = servletRequest.getParameter("clientId");
        if (clientId != null && clientId.isEmpty()) {
            servletRequest.setAttribute(MESSAGE_ATTRIBUTE, "Empty client id.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        String age = servletRequest.getParameter("age");
        if (age != null && age.isEmpty()) {
            servletRequest.setAttribute(MESSAGE_ATTRIBUTE, "Empty age.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        if ("POST".equals(method)) {

            String parameterMethod = servletRequest.getParameter("method");
            if ("put".equals(parameterMethod) || "delete".equals(parameterMethod)) {

                try {
                    validationService.validateClientExists(Long.parseLong(clientId));
                } catch (BusinessException e) {
                    servletRequest.setAttribute(MESSAGE_ATTRIBUTE, e.getMessage());
                    servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
                    return;
                }
            }

            if ("put".equals(parameterMethod) || parameterMethod == null) {

                String email = servletRequest.getParameter("email");
                String phone = servletRequest.getParameter("phone");
                try {
                    validationService.validateClientField(Integer.parseInt(age), email, phone);
                } catch(NumberFormatException|BusinessException e) {
                    servletRequest.setAttribute(MESSAGE_ATTRIBUTE, e.getMessage());
                    servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
                    return;
                }
            }
        }

         filterChain.doFilter(servletRequest, servletResponse);
    }
}
