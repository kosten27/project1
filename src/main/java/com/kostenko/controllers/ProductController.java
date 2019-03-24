package com.kostenko.controllers;

import com.kostenko.domain.Product;
import com.kostenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProducts(ModelMap modelMap) {
        List<String> products = new ArrayList<>();
        for (Product product : productService.getProducts()) {
            products.add(product.toString());
        }
        modelMap.put("products", products);
        return "products/products";
    }

    @PostMapping("/products")
    public String postProduct(ModelMap modelMap, @RequestParam(required = false) String method, @RequestParam(required = false) Long productId, @RequestParam(required = false) String name, @RequestParam(required = false)
    BigDecimal price) {
        if ("put".equals(method)) {
            return putProduct(modelMap, productId, name, price);
        } else if ("delete".equals(method)) {
            return deleteProduct(modelMap, productId);
        }

        productService.createProduct(name, price);
        return showProducts(modelMap);
    }

    @PutMapping("/products")
    public String putProduct(ModelMap modelMap, @RequestParam long productId, @RequestParam String name, @RequestParam BigDecimal price) {
        productService.modifyProduct(productId, name, price);
        return showProducts(modelMap);
    }

    @DeleteMapping("/products")
    public String deleteProduct(ModelMap modelMap, @RequestParam long productId) {
        productService.deleteProduct(productId);
        return showProducts(modelMap);
    }

    @GetMapping("/products/addProduct")
    public String mappingAddProduct(ModelMap modelMap) {
        return "products/addProduct";
    }

    @GetMapping("/products/modifyProduct")
    public String mappingModifyProduct(ModelMap modelMap) {
        return "products/modifyProduct";
    }

    @GetMapping("/products/deleteProduct")
    public String mappingDeleteProduct(ModelMap modelMap) {
        return "products/deleteProduct";
    }
}
