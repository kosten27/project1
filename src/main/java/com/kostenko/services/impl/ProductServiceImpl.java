package com.kostenko.services.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.ProductDaoImpl;
import com.kostenko.domain.Product;
import com.kostenko.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void createProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        boolean result = productDao.saveProduct(product);
        if(result) {
            System.out.println("Product saved: " + product);
        }
    }

    @Override
    public void modifyProduct(long id, String newName, BigDecimal newPrice) {
        Product product = new Product(id);
        product.setName(newName);
        product.setPrice(newPrice);
        boolean result = productDao.saveProduct(product);
        if(result) {
            System.out.println("Product modified: " + product);
        }
    }

    @Override
    public void deleteProduct(long id) {
        Product product = new Product(id);
        boolean result = productDao.deleteProduct(product);
        if(result) {
            System.out.println("Product deleted: " + product);
        }
    }

    @Override
    public void showProducts() {
        List<Product> products = productDao.getProducts();
        for (Product product: products) {
            System.out.println(product);
        }
    }
}
