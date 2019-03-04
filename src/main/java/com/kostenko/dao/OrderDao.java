package com.kostenko.dao;

import com.kostenko.domain.Client;
import com.kostenko.domain.Order;

import java.util.List;

public interface OrderDao {

    /**
     * The OrderDao class declares methods for managing orders in the database as a client.
     *
     * @author Artem Kostenko
     */

    boolean saveOrder(Order order);

    boolean deleteOrder(long orderId);

    boolean updateOrder(Order order);

    Order getOrder(long orderId);

    List<Order> getOrders();

    List<Order> getOrders(long clientId);
}
