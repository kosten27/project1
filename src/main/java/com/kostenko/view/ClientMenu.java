package com.kostenko.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientMenu {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public void show() throws IOException {
        boolean isRuning = true;
        while (isRuning) {
            showMenu();
            switch (br.readLine()) {
                case "1":
                    System.out.println("Add order");
                    break;
                case "9":
                    isRuning = false;
                    break;
                case "10":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Add order");
        System.out.println("9. Return");
        System.out.println("10. Exit");
    }
}
