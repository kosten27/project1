package com.kostenko.services;

import java.math.BigDecimal;

public interface ProductService {
    void createProduct(String name, BigDecimal price);
    void modifyProduct(long id, String newName, BigDecimal price);
    void deleteProduct(long id);
    void showProducts();

}
