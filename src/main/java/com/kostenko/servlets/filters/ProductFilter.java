package com.kostenko.servlets.filters;

import javax.servlet.*;
import java.io.IOException;

public class ProductFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String productId = servletRequest.getParameter("productId");
        if (productId != null && productId.isEmpty()) {
            servletRequest.setAttribute("message", "Empty product id.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        String price = servletRequest.getParameter("price");
        if (price != null && price.isEmpty()) {
            servletRequest.setAttribute("message", "Empty price.");
            servletRequest.getRequestDispatcher("message.jsp").forward(servletRequest, servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
