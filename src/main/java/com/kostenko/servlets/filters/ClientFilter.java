package com.kostenko.servlets.filters;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.exceptions.BusinessException;
import com.kostenko.validators.ValidationService;
import com.kostenko.validators.impl.ValidationServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/clients")
public class ClientFilter implements Filter{

    private ValidationService validationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DataSourceDB dataSource = new DataSourceDB();
        this.validationService = new ValidationServiceImpl(new ClientDBDao(dataSource));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String age = servletRequest.getParameter("age");
        try {
            validationService.validateAge(Integer.parseInt(age));
        } catch(NumberFormatException|BusinessException e) {
            PrintWriter writer = servletResponse.getWriter();
            writer.println("<h2> WRONG AGE!!! </h2>");
            return;
         }
         filterChain.doFilter(servletRequest, servletResponse);
    }
}
