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
        if (client != null) {

            List<Product> products = new ArrayList<>();
            for (long productId: productsId) {
                Product product = productDao.getProduct(productId);
                if (product != null) {
                    products.add(product);
                } else {
                    System.out.println("Product with id " + productId + " wasn't fount. Order not saved.");
                    return;
                }
            }
            Order order = new Order(client, products);
            boolean result = orderDao.saveOrder(order);
            if (result) {
                System.out.println("Order saved: " + order);
            } else {
                System.out.println("Order not saved.");
            }
        } else {
            System.out.println("Client wasn't found. Order not saved.");
        }
    }

    @Override
    public boolean orderFound(long orderId) {
        return orderDao.orderFound(orderId);
    }

    @Override
    public void modifyOrder(long orderId, List<Long> productsId) {
        Order order = orderDao.getOrder(orderId);
        if (order != null) {

            List<Product> products = new ArrayList<>();
            for (long productId : productsId) {
                Product product = productDao.getProduct(productId);
                if (product != null) {
                    products.add(product);
                } else {
                    System.out.println("Product with id " + productId + " wasn't fount. Order wasn't modify.");
                    return;
                }
            }
            order.setProducts(products);
            boolean result = orderDao.updateOrder(order);
            if (result) {
                System.out.println("Order modified: " + order);
            } else {
                System.out.println("Order wasn't modify.");
            }
        } else {
            System.out.println("Order wasn't found.");
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
