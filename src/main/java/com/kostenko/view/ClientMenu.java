package com.kostenko.view;

import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Component
public class ClientMenu {
    @Autowired
    private final BufferedReader br;
    private long clientId;
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final ClientService clientService;
    @Autowired
    private final ProductService productService;

    public ClientMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {
        boolean isRuning = true;
        boolean needReturn = false;
        while (isRuning) {

            System.out.println("1. Authorize");
            System.out.println("2. Register");
            System.out.println("R. Return");
            switch (br.readLine()) {
                case "1":
                    if (authorize()) {

                        isRuning = false;
                    }
                    break;
                case "2":
                    if (register()) {

                        isRuning = false;
                    }
                    break;
                case "R":
                    needReturn = true;
                    isRuning = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
        if (needReturn) {
            return;
        }
        isRuning = true;
        while (isRuning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    modify();
                    break;
                case "2":
                    showProducts();
                    break;
                case "3":
                    createOrder();
                    break;
                case "4":
                    showOrders();
                    break;
                case "R":
                    isRuning = false;
                    break;
                case "E":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private boolean authorize() throws IOException {
        System.out.println("Input client id for authorization:");
        clientId = Long.valueOf(br.readLine());
        if (clientService.clientFound(clientId)) {
            return true;
        } else {
            System.out.println("Client wasn't found");
            return false;
        }

    }

    private void showProducts() {
        productService.showProducts();
    }

    private void modify() throws IOException {
        System.out.println("Input new name:");
        String name = br.readLine();
        System.out.println("Input new surname:");
        String surname = br.readLine();
        System.out.println("Input new age:");
        int age = readInteger();
        System.out.println("Input new email:");
        String email = br.readLine();
        System.out.println("Input new phone:");
        String phone = br.readLine();
        clientService.modifyClient(clientId, name, surname, age, email, phone);
    }

    private boolean register() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input surname:");
        String surname = br.readLine();
        System.out.println("Input phone:");
        String phone = br.readLine();
        System.out.println("Input age:");
        int age = readInteger();
        System.out.println("Input email:");
        String email = br.readLine();
        clientId = clientService.createClient(name, surname, age, email, phone);
        if (clientId < 0) {
            return false;
        } else {
            return true;
        }
    }

    private int readInteger() {
        try {
            return Integer.valueOf(br.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Input number please!!");
            return readInteger();
        }
    }

    private void showOrders() {
        orderService.showOrders(clientId);
    }

    private void createOrder() throws IOException {
        boolean isRuning = true;
        ArrayList<Long> productsId = new ArrayList<>();
        while (isRuning) {

            System.out.println("1. Add product");
            System.out.println("2. Save order");
            switch (br.readLine()) {
                case "1":
                    System.out.println("Input product id:");
                    long productId = Long.valueOf(br.readLine());
                    if (productService.productFound(productId)) {

                        productsId.add(productId);
                    } else {
                        System.out.println("Product wasn't found");
                    }
                    break;
                case "2":
                    orderService.createOrder(clientId, productsId);
                    isRuning = false;
                    break;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Modify");
        System.out.println("2. List products");
        System.out.println("3. Add order");
        System.out.println("4. List orders");
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }
}
