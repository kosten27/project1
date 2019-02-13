package com.kostenko.services;

import java.math.BigDecimal;

public interface ProductService {

    /**
     * The ProductService class declares methods for managing products as an administrator.
     *
     * @author Artem Kostenko
     */

    void createProduct(String name, BigDecimal price);

    void modifyProduct(long id, String newName, BigDecimal price);

    void deleteProduct(long id);

    void showProducts();

}
