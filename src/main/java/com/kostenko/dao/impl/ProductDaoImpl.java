package com.kostenko.dao.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {

    private Map<Long, Product> map = new HashMap();
    private static long generator = 0;

    @Override
    public boolean saveProduct(Product product) {
        System.out.println("Saving product.... Please wait");
        product.setId(generator++);
        map.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        System.out.println("Updating product.... Please wait");
        map.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean deleteProduct(long productId) {
        System.out.println("Deleting product.... Please wait");
        if (map.containsKey(productId)) {
            map.remove(productId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Product getProduct(long id) {
        return map.get(id);
    }

    @Override
    public List<Product> getProducts() {
        System.out.println("Getting products.... Please wait");
        return new ArrayList<>(map.values());
    }
}
