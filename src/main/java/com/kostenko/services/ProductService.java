package com.kostenko.services;

import com.kostenko.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * The ProductService class declares methods for managing products as an administrator.
     *
     * @author Artem Kostenko
     */

    void createProduct(String name, BigDecimal price);

    boolean productFound(long id);

    void modifyProduct(long id, String newName, BigDecimal price);

    void deleteProduct(long id);

    void showProducts();

    List<Product> getProducts();

}
