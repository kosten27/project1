package com.kostenko;

import com.kostenko.view.MainMenu;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app.xml");
        MainMenu mainMenu = (MainMenu) context.getBean(MainMenu.class);
        mainMenu.showMenu();
        context.close();
    }
}
