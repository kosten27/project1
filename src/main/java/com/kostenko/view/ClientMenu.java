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
        System.out.println("Input client id for authorization:");
        clientId = Long.valueOf(br.readLine());
        while (isRuning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    register();
                    break;
                case "2":
                    modify();
                    break;
                case "3":
                    showProducts();
                    break;
                case "4":
                    createOrder();
                    break;
                case "5":
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

    private void showProducts() {
        productService.showProducts();
    }

    private void modify() throws IOException {
        System.out.println("Input new name:");
        String newName = br.readLine();
        System.out.println("Input new surname:");
        String newSurname = br.readLine();
        System.out.println("Input new phone:");
        String newPhone = br.readLine();
        clientService.modifyClient(clientId, newName, newSurname, newPhone);
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
        clientService.createClient(name, surname, age, email, phone);
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
        System.out.println("1. Register");
        System.out.println("2. Modify");
        System.out.println("3. List products");
        System.out.println("4. Add order");
        System.out.println("5. List orders");
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }
}
