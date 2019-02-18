package com.kostenko.view;

import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.services.impl.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClientMenu {
    private final BufferedReader br;
    private long clientId;
    private final OrderService orderService;
    private final ClientService clientService;
    private final ProductService productService;

    public ClientMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.productService = productService;
        this.orderService = orderService;
    }

    public void show() throws IOException {
        boolean isRuning = true;
        while (isRuning) {

            System.out.println("1. Authorize");
            System.out.println("2. Register");
            switch (br.readLine()) {
                case "1":
                    authorize();
                    isRuning = false;
                    break;
                case "2":
                    register();
                    isRuning = false;
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
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

    private void authorize() throws IOException {
        System.out.println("Input client id for authorization:");
        clientId = Long.valueOf(br.readLine());
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

    private void register() throws IOException {
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
                    productsId.add(Long.valueOf(br.readLine()));
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
