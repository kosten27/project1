package com.kostenko.services.impl;

import com.kostenko.dao.OrderDao;
import com.kostenko.dao.impl.OrderDaoImpl;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;
import com.kostenko.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(long clientId, List<Long> productsId) {
        Client client = new Client(clientId);
        List<Product> products = new ArrayList<>();
        for (long id: productsId) {
            products.add(new Product(id));
        }
        Order order = new Order(client, products);
        boolean result = orderDao.saveOrder(order);
        if (result) {
            System.out.println("Order saved: " + order);
        }
    }

    @Override
    public void modifyOrder(long orderId, long clientId, List<Long> productsId) {
        Client client = new Client(clientId);
        List<Product> products = new ArrayList<>();
        for (long id: productsId) {
            products.add(new Product(id));
        }
        Order order = new Order(orderId, client);
        order.setProducts(products);
        boolean result = orderDao.saveOrder(order);
        if (result) {
            System.out.println("Order modified: " + order);
        }
    }

    @Override
    public void deleteOrder(long orderId) {
        Order order = new Order(orderId);
        boolean result = orderDao.deleteOrder(order);
        if(result) {
            System.out.println("Order deleted: " + order);
        }
    }

    @Override
    public void deleteOrder(long orderId, long clientId) {
        Client client = new Client(clientId);
        Order order = new Order(orderId, client);
        boolean result = orderDao.deleteOrder(order);
        if(result) {
            System.out.println("Order deleted: " + order);
        }
    }

    @Override
    public void showOrders() {
        List<Order> orders = orderDao.getOrders();
        System.out.println("List all orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Override
    public void showOrders(long clientId) {
        Client client = new Client(clientId);
        List<Order> orders = orderDao.getOrders(client);
        System.out.println("List all orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
