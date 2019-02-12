package com.kostenko.dao;

import com.kostenko.domain.Product;

import java.util.List;

public interface ProductDao {
    boolean saveProduct(Product product);
    boolean deleteProduct(Product product);
    Product[] getProducts();
}
