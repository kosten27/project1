package com.kostenko.view;

import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class AdminMenu {
    private final BufferedReader br;
    private final ClientService clientService;
    private final OrderService orderService;

    private final ProductService productService;

    public AdminMenu(BufferedReader br, ClientService clientService, ProductService productService, OrderService orderService) {
        this.br = br;
        this.clientService = clientService;
        this.orderService = orderService;
        this.productService = productService;
    }

    public void show() throws IOException {

        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    modifyClient();
                    break;
                case "3":
                    removeClient();
                    break;
                case "4":
                    showAllClients();
                    break;
                case "5":
                    createProduct();
                    break;
                case "6":
                    modifyProduct();
                    break;
                case "7":
                    deleteProduct();
                    break;
                case "8":
                    showProducts();
                    break;
                case "9":
                    modifyOrder();
                    break;
                case "10":
                    removeOrder();
                    break;
                case "11":
                    showOrders();
                    break;
                case "R":
                    return;
                case "E":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void showOrders() {
        orderService.showOrders();
    }

    private void removeOrder() throws IOException {
        System.out.println("Input order id:");
        long orderId = Long.valueOf(br.readLine());
        orderService.deleteOrder(orderId);
    }

    private void modifyOrder() throws IOException {
        boolean isRuning = false;
        System.out.println("Input order id:");
        long orderId = Long.valueOf(br.readLine());
        System.out.println("Input id of new client:");
        long clientId = Long.valueOf(br.readLine());
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

    private void showAllClients() {
        for (Client client : clientService.getAllClients()) {
            System.out.println(client);
        }
    }

    private void showProducts() {
        productService.showProducts();
    }

    private void deleteProduct() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        productService.deleteProduct(id);
    }

    private void modifyProduct() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        System.out.println("Input new name:");
        String newName = br.readLine();
        System.out.println("Input new price:");
        BigDecimal newPrice = new BigDecimal(br.readLine());
        productService.modifyProduct(id, newName, newPrice);
    }

    private void createProduct() throws IOException {
        System.out.println("Input name:");
        String name = br.readLine();
        System.out.println("Input price:");
        BigDecimal price = new BigDecimal(br.readLine());
        productService.createProduct(name, price);
    }

    private void removeClient() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        clientService.deleteClient(id);
    }

    private void modifyClient() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        System.out.println("Input new name:");
        String newName = br.readLine();
        System.out.println("Input new surname:");
        String newSurname = br.readLine();
        System.out.println("Input new phone:");
        String newPhone = br.readLine();
        clientService.modifyClient(id, newName, newSurname, newPhone);
    }

    private void createClient() throws IOException {
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

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("5. Add product");
        System.out.println("6. Modify product");
        System.out.println("7. Remove product");
        System.out.println("8. List all products");
        System.out.println("9. Modify order");
        System.out.println("10. Remove order");
        System.out.println("11. List all orders");
        System.out.println("R. Return");
        System.out.println("E. Exit");
    }
}
