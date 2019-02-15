package com.kostenko;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.impl.ClientDaoImpl;
import com.kostenko.services.ClientService;
import com.kostenko.services.impl.ClientServiceImpl;
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
        ClientDao clientDao = ClientDaoImpl.getInstance();
        ValidationService validationService = new ValidationServiceImpl();
        ClientService clientService = new ClientServiceImpl(clientDao, validationService);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AdminMenu adminMenu = new AdminMenu(br, clientService);
        ClientMenu clientMenu = new ClientMenu(br, clientService);

        MainMenu menu = new MainMenu(br, adminMenu, clientMenu);
        menu.showMenu();
    }
}
