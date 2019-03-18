package com.kostenko.services;

import com.kostenko.domain.Order;

import java.util.List;

public interface OrderService {

    /**
     * The OrderService class declares methods for managing orders as a client.
     *
     * @author Artem Kostenko
     */

    void createOrder(long clientId, List<Long> productsId);

    boolean orderFound(long orderId);

    void modifyOrder(long orderId, List<Long> productsId);

    void deleteOrder(long orderId);

    void showOrders();

    void showOrders(long clientId);

    List<Order> getOrders();
}
