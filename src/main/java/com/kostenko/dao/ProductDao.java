package com.kostenko.dao;

import com.kostenko.domain.Product;

import java.util.List;

public interface ProductDao {

    /**
     * The ProductDao class declares methods for managing products in the database as an administrator.
     *
     * @author Artem Kostenko
     */

    boolean saveProduct(Product product);

    boolean updateProduct(Product product);

    boolean deleteProduct(Product product);

    Product getProduct(long id);

    List<Product> getProducts();
}
