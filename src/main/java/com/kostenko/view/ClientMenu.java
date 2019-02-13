package com.kostenko.view;

import com.kostenko.services.OrderService;
import com.kostenko.services.impl.OrderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ClientMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private long clientId;
    private final OrderService orderService = new OrderServiceImpl();

    public void show() throws IOException {
        boolean isRuning = true;
        System.out.println("Input client id:");
        clientId = Long.valueOf(br.readLine());
        while (isRuning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    modifyOrder();
                    break;
                case "3":
                    removeOrder();
                    break;
                case "4":
                    showOrders();
                    break;
                case "5":
                    isRuning = false;
                    break;
                case "6":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void showOrders() {
        orderService.showOrders(clientId);
    }

    private void removeOrder() throws IOException {
        System.out.println("Input order id:");
        long orderId = Long.valueOf(br.readLine());
        orderService.deleteOrder(orderId, clientId);
    }

    private void modifyOrder() throws IOException {
        boolean isRuning = true;
        System.out.println("Input order id:");
        long orderId = Long.valueOf(br.readLine());
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
        System.out.println("1. Add order");
        System.out.println("2. Modify order");
        System.out.println("3. Remove order");
        System.out.println("4. List all order");
        System.out.println("5. Return");
        System.out.println("6. Exit");
    }
}
