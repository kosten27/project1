package com.kostenko.servlets;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.dao.impl.OrderDBDao;
import com.kostenko.dao.impl.ProductDBDao;
import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.services.impl.OrderServiceImpl;
import com.kostenko.services.impl.ProductServiceImpl;
import com.kostenko.servlets.filters.ClientFilter;
import com.kostenko.servlets.filters.OrderFilter;
import com.kostenko.servlets.filters.ProductFilter;
import com.kostenko.validators.impl.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebApp implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        DataSourceDB dataSource = new DataSourceDB();
//        ClientDao clientDao = new ClientDBDao(dataSource);
//        ProductDao productDao = new ProductDBDao(dataSource);
//        ValidationServiceImpl validationService = new ValidationServiceImpl(clientDao);
//        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
//        ProductService productService = new ProductServiceImpl(productDao);
//        OrderService orderService = new OrderServiceImpl(new OrderDBDao(dataSource), clientDao, productDao);
//
//        ServletContext servletContext = sce.getServletContext();
//        servletContext.addServlet("ClientServlet", new ClientServlet(clientService)).addMapping("/clients");
//        servletContext.addServlet("ProductServlet", new ProductServlet(productService)).addMapping("/products");
//        servletContext.addServlet("OrderServlet", new OrderServlet(orderService)).addMapping("/orders");
//        servletContext.addFilter("ClientFilter", new ClientFilter(validationService)).addMappingForServletNames(null, false, "ClientServlet");
//        servletContext.addFilter("ProductFilter", new ProductFilter()).addMappingForServletNames(null, false, "ProductServlet");
//        servletContext.addFilter("OrderFilter", new OrderFilter()).addMappingForServletNames(null, false, "OrderServlet");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
