package com.kostenko.controllers.filters;

import org.springframework.stereotype.Controller;

import javax.servlet.*;
import java.io.IOException;

public class OrderFilter implements Filter {

    public static final String MESSAGE_PAGE = "message";
    public static final String MESSAGE_ATTRIBUTE = "message";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String orderId = servletRequest.getParameter("orderId");
        if (orderId != null && orderId.isEmpty()) {
            servletRequest.setAttribute(MESSAGE_ATTRIBUTE, "Empty order id.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        String clientId = servletRequest.getParameter("clientId");
        if (clientId != null && clientId.isEmpty()) {
            servletRequest.setAttribute(MESSAGE_ATTRIBUTE, "Empty client id.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
