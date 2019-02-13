package com.kostenko.dao.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public boolean saveProduct(Product product) {
        System.out.println("Saving product.... Please wait");
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        System.out.println("Deleting product.... Please wait");
        return true;
    }

    @Override
    public List<Product> getProducts() {
        System.out.println("Getting products.... Please wait");
        List<Product> products = new ArrayList<>();
        return products;
    }
}
