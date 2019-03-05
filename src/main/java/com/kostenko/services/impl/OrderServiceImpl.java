package com.kostenko.services.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.OrderDao;
import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.OrderDaoImpl;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;
import com.kostenko.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ClientDao clientDao;
    private final ProductDao productDao;

    public OrderServiceImpl(OrderDao orderDao, ClientDao clientDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.clientDao = clientDao;
        this.productDao = productDao;
    }

    @Override
    public void createOrder(long clientId, List<Long> productsId) {
        Client client = clientDao.getClient(clientId);
        List<Product> products = new ArrayList<>();
        for (long productId: productsId) {
            products.add(productDao.getProduct(productId));
        }
        Order order = new Order(client, products);
        boolean result = orderDao.saveOrder(order);
        if (result) {
            System.out.println("Order saved: " + order);
        }
    }

    @Override
    public boolean orderFound(long orderId) {
        return orderDao.orderFound(orderId);
    }

    @Override
    public void modifyOrder(long orderId, List<Long> productsId) {
        Order order = orderDao.getOrder(orderId);
        List<Product> products = new ArrayList<>();
        for (long productId : productsId) {
            products.add(productDao.getProduct(productId));
        }
        order.setProducts(products);
        boolean result = orderDao.updateOrder(order);
        if (result) {
            System.out.println("Order modified: " + order);
        }
    }

    @Override
    public void deleteOrder(long orderId) {
        boolean result = orderDao.deleteOrder(orderId);
        if(result) {
            System.out.println("Deleted order with id: " + orderId);
        } else {
            System.out.println("Can't delete order with id: " + orderId);
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
        List<Order> orders = orderDao.getOrders(clientId);
        System.out.println("List all orders:");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
