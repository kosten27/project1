package com.kostenko.servlets;

import com.kostenko.domain.Product;
import com.kostenko.services.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {

    ProductService productService;

    public ProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> products = new ArrayList<>();
        for (Product product : productService.getProducts()) {
            products.add(product.toString());
        }
        req.setAttribute("products", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("products/products.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if ("delete".equals(method)) {
            doDelete(req, resp);
            return;
        } else if ("put".equals(method)) {
            doPut(req, resp);
            return;
        }

        String name = req.getParameter("name");
        String price = req.getParameter("price");
        productService.createProduct(name, new BigDecimal(price));
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        productService.modifyProduct(Long.parseLong(productId), name, new BigDecimal(price));
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        productService.deleteProduct(Long.parseLong(productId));
        doGet(req, resp);
    }
}
