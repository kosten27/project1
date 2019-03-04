package com.kostenko.services;

import java.util.List;

public interface OrderService {

    /**
     * The OrderService class declares methods for managing orders as a client.
     *
     * @author Artem Kostenko
     */

    void createOrder(long clientId, List<Long> productsId);

    void modifyOrder(long orderId, long clientId, List<Long> productsId);

    void deleteOrder(long orderId);

    void showOrders();

    void showOrders(long clientId);
}
