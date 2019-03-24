package com.kostenko.controllers.filters;

import javax.servlet.*;
import java.io.IOException;

public class ProductFilter implements Filter {

    public static final String MESSAGE_PAGE = "message";
    public static final String MESSAGE_ATTRIBUTE = "message";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String productId = servletRequest.getParameter("productId");
        if (productId != null && productId.isEmpty()) {
            servletRequest.setAttribute(MESSAGE_ATTRIBUTE, "Empty product id.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        String price = servletRequest.getParameter("price");
        if (price != null && price.isEmpty()) {
            servletRequest.setAttribute("message", "Empty price.");
            servletRequest.getRequestDispatcher(MESSAGE_PAGE).forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
