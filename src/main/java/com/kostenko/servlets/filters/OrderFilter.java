package com.kostenko.servlets.filters;

import javax.servlet.*;
import java.io.IOException;

public class OrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String orderId = servletRequest.getParameter("orderId");
        if (orderId != null && orderId.isEmpty()) {
            servletRequest.setAttribute("message", "Empty order id.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        String clientId = servletRequest.getParameter("clientId");
        if (clientId != null && clientId.isEmpty()) {
            servletRequest.setAttribute("message", "Empty client id.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
