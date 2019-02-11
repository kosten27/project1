package com.kostenko.view;

import com.kostenko.services.ClientService;
import com.kostenko.services.impl.ClientServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ClientService clientService = new ClientServiceImpl();
    public void show() throws IOException {

        while (true) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    createClient();
                    break;
                case "2":
                    System.out.println("Modify client");
                    break;
                case "3":
                    System.out.println("Remove client");
                    break;
                case "4":
                    System.out.println("List all clients");
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void createClient() throws IOException {
        System.out.println("Input name: ");
        String name = br.readLine();
        System.out.println("Input surname: ");
        String surmane = br.readLine();
        System.out.println("Input phone: ");
        String phone = br.readLine();
        clientService.createClient(name, surmane, phone);
    }

    private void showMenu() {
        System.out.println("1. Add client");
        System.out.println("2. Modify client");
        System.out.println("3. Remove client");
        System.out.println("4. List all clients");
        System.out.println("9. Return");
        System.out.println("10. Exit");
    }
}
