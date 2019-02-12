package com.kostenko.view;

import com.kostenko.services.ClientService;
import com.kostenko.services.ProductService;
import com.kostenko.services.impl.ClientServiceImpl;
import com.kostenko.services.impl.ProductServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class AdminMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

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
                    showClients();
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
                    return;
                case "10":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void showClients() {
        clientService.showClients();
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
        String surmane = br.readLine();
        System.out.println("Input phone:");
        String phone = br.readLine();
        clientService.createClient(name, surmane, phone);
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
        System.out.println("9. Return");
        System.out.println("10. Exit");
    }
}
