package com.kostenko;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.DataSourceDB;
import com.kostenko.dao.OrderDao;
import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.*;
import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.services.impl.OrderServiceImpl;
import com.kostenko.services.impl.ProductServiceImpl;
import com.kostenko.validators.ValidationService;
import com.kostenko.validators.impl.ValidationServiceImpl;
import com.kostenko.view.AdminMenu;
import com.kostenko.view.ClientMenu;
import com.kostenko.view.MainMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContextExtensionsKt;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {
//        DataSourceDB dataSource = new DataSourceDB();
//        ClientDao clientDao = new ClientDBDao(dataSource);
//        ProductDao productDao = new ProductDBDao(dataSource);
//        OrderDao orderDao = new OrderDBDao(dataSource);
//
//        ValidationService validationService = new ValidationServiceImpl(clientDao);
//        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
//        ProductService productService = new ProductServiceImpl(productDao);
//        OrderService orderService = new OrderServiceImpl(orderDao, clientDao, productDao);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
//        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);
//
//        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
//        menu.showMenu();

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.kostenko");
        MainMenu mainMenu = (MainMenu) context.getBean(MainMenu.class);
        mainMenu.showMenu();
        context.close();
    }
}
