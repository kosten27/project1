package com.kostenko.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "icnrement", strategy = "increment")
    private long id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;
    @ManyToMany
    @JoinTable(name = "PRODUCT_IN_ORDER",
            joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"))
    List<Product> products;

    public Order() {
    }

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

    public Order(Client client) {
        this.client = client;
    }

    public Order(long id, Client client, List<Product> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    public void setId(long id) {
        this.id = id;
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
