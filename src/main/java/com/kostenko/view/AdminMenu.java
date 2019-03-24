package com.kostenko.view;

import com.kostenko.domain.Client;
import com.kostenko.services.ClientService;
import com.kostenko.services.OrderService;
import com.kostenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

@Component()
public class AdminMenu {
    @Autowired
    private final BufferedReader br;
    @Autowired
    private final ClientService clientService;
    @Autowired
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
                    removeProduct();
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
        boolean isRuning = true;
        System.out.println("Input order id:");
        long orderId = Long.valueOf(br.readLine());
        if (orderService.orderFound(orderId)) {
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
                        orderService.modifyOrder(orderId, productsId);
                        isRuning = false;
                        break;
                    default:
                        System.out.println("Wrong input");
                }
            }
        } else {
            System.out.println("Order wasn't found");
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

    private void removeProduct() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        productService.deleteProduct(id);
    }

    private void modifyProduct() throws IOException {
        System.out.println("Input id:");
        long id = Long.valueOf(br.readLine());
        if (productService.productFound(id)) {
            System.out.println("Input new name:");
            String newName = br.readLine();
            System.out.println("Input new price:");
            BigDecimal newPrice = new BigDecimal(br.readLine());
            productService.modifyProduct(id, newName, newPrice);
        } else {
            System.out.println("Product wasn't found");
        }
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
        long clientId = Long.valueOf(br.readLine());
        if (clientService.clientFound(clientId)) {
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
        } else {
            System.out.println("Client not fount");
        }
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
