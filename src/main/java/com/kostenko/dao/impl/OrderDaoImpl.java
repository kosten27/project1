package com.kostenko.dao.impl;

import com.kostenko.dao.OrderDao;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean saveOrder(Order order) {
        System.out.println("Saving order.... Please wait");
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        System.out.println("Deleting order.... Please wait");
        return true;
    }

    @Override
    public List<Order> getOrders(Client client) {
        System.out.println("Getting orders.... Please wait");
        List<Order> orders = new ArrayList<>();
        return orders;
    }
}
