package com.kostenko.servlets;

import com.kostenko.domain.Order;
import com.kostenko.services.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderServlet extends HttpServlet {

    OrderService orderService;

    public OrderServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> orders = new ArrayList<>();
        for (Order order : orderService.getOrders()) {
            orders.add(order.toString());
        }
        req.setAttribute("orders", orders);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("orders/orders.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if ("delete".equals(method)) {
            doDelete(req, resp);
            return;
        } else if ("put".equals(method)) {
            doPut(req, resp);
            return;
        }

        String clientId = req.getParameter("clientId");
        String productId1 = req.getParameter("productId1");
        String productId2 = req.getParameter("productId2");
        String productId3 = req.getParameter("productId3");
        String productId4 = req.getParameter("productId4");
        String productId5 = req.getParameter("productId5");

        List<Long> productsId = new ArrayList<>();
        if (productId1 != null && !productId1.isEmpty()) {
            productsId.add(Long.parseLong(productId1));
        }
        if (productId2 != null && !productId2.isEmpty()) {
            productsId.add(Long.parseLong(productId2));
        }
        if (productId3 != null && !productId3.isEmpty()) {
            productsId.add(Long.parseLong(productId3));
        }
        if (productId4 != null && !productId4.isEmpty()) {
            productsId.add(Long.parseLong(productId4));
        }
        if (productId5 != null && !productId5.isEmpty()) {
            productsId.add(Long.parseLong(productId5));
        }
        orderService.createOrder(Long.parseLong(clientId), productsId);
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String orderId = req.getParameter("orderId");
        String productId1 = req.getParameter("productId1");
        String productId2 = req.getParameter("productId2");
        String productId3 = req.getParameter("productId3");
        String productId4 = req.getParameter("productId4");
        String productId5 = req.getParameter("productId5");

        List<Long> productsId = new ArrayList<>();
        if (productId1 != null && !productId1.isEmpty()) {
            productsId.add(Long.parseLong(productId1));
        }
        if (productId2 != null && !productId2.isEmpty()) {
            productsId.add(Long.parseLong(productId2));
        }
        if (productId3 != null && !productId3.isEmpty()) {
            productsId.add(Long.parseLong(productId3));
        }
        if (productId4 != null && !productId4.isEmpty()) {
            productsId.add(Long.parseLong(productId4));
        }
        if (productId5 != null && !productId5.isEmpty()) {
            productsId.add(Long.parseLong(productId5));
        }
        orderService.modifyOrder(Long.parseLong(orderId), productsId);
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        orderService.deleteOrder(Long.parseLong(orderId));
        doGet(req, resp);
    }
}
