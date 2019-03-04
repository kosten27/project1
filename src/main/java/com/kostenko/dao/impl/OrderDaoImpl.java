package com.kostenko.dao.impl;

import com.kostenko.dao.OrderDao;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {
    Map<Long, Order> map = new HashMap<>();
    long generator = 0;


    @Override
    public boolean saveOrder(Order order) {
        System.out.println("Saving order.... Please wait");
        order.setId(generator++);
        map.put(order.getId(), order);
        return true;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        System.out.println("Deleting order.... Please wait");
        if (map.containsKey(orderId)) {
            map.remove(orderId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public Order getOrder(long orderId) {
        return map.get(orderId);
    }

    @Override
    public List<Order> getOrders() {
        System.out.println("Getting orders.... Please wait");
        return new ArrayList<>(map.values());
    }

    @Override
    public List<Order> getOrders(long clientId) {
        System.out.println("Getting orders.... Please wait");
        List<Order> orders = new ArrayList<>();
        for (Map.Entry<Long, Order> entry : map.entrySet()) {
            if (entry.getValue().getClient().getId() == clientId) {
                orders.add(entry.getValue());
            }
        }
        return orders;
    }
}
