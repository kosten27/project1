package com.kostenko.services.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.ProductDaoImpl;
import com.kostenko.domain.Product;
import com.kostenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
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
        } else {
            System.out.println("Product not saved.");
        }
    }

    @Override
    public boolean productFound(long id) {
        return productDao.productFound(id);
    }

    @Override
    public void modifyProduct(long productId, String newName, BigDecimal newPrice) {
        Product product = productDao.getProduct(productId);
        if (product != null) {
            product.setName(newName);
            product.setPrice(newPrice);
            boolean result = productDao.updateProduct(product);
            if(result) {
                System.out.println("Product modified: " + product);
            } else {
                System.out.println("Product wasn't modify.");
            }
        } else {
            System.out.println("Product wasn't found.");
        }
    }

    @Override
    public void deleteProduct(long productId) {
        boolean result = productDao.deleteProduct(productId);
        if(result) {
            System.out.println("Deleted product with id: " + productId);
        } else {
            System.out.println("Can't delete product with id: " + productId);
        }
    }

    @Override
    public void showProducts() {
        List<Product> products = productDao.getProducts();
        for (Product product: products) {
            System.out.println(product);
        }
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }
}
