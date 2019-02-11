package com.kostenko;

import com.kostenko.view.MainMenu;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        MainMenu menu = new MainMenu();
        menu.showMenu();
    }
}
