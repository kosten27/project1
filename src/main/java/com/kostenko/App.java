package com.kostenko;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.OrderDao;
import com.kostenko.dao.ProductDao;
import com.kostenko.dao.impl.ClientDBDao;
import com.kostenko.dao.impl.ClientDaoImpl;
import com.kostenko.dao.impl.OrderDaoImpl;
import com.kostenko.dao.impl.ProductDaoImpl;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {
        ClientDao clientDao = new ClientDBDao();
        ProductDao productDao = new ProductDaoImpl();
        OrderDao orderDao = new OrderDaoImpl();

        ValidationService validationService = new ValidationServiceImpl(clientDao);
        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        ProductService productService = new ProductServiceImpl(productDao);
        OrderService orderService = new OrderServiceImpl(orderDao, clientDao, productDao);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        AdminMenu adminMenu = new AdminMenu(br, clientService, productService, orderService);
        ClientMenu clientMenu = new ClientMenu(br, clientService, productService, orderService);

        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}
