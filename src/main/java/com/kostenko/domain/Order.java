package com.kostenko.domain;

import java.util.List;

public class Order {
    private long id;
    private Client client;
    List<Product> products;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", products=" + products +
                '}';
    }

    public Order(Client client, List<Product> products) {
        this.client = client;
        this.products = products;
    }

    public Order(long id) {
        this.id = id;
    }

    public Order(long id, Client client) {
        this.id = id;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public List<Product> getProducts() {
        return products;
    }
}
