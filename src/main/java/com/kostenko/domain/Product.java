package com.kostenko.domain;

import java.math.BigDecimal;

public class Product {
    private long id;
    private String name;
    private BigDecimal price;

    public Product(long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
