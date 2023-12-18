package com.example.model;

import com.example.view.Login;
import com.example.view.Register;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class AppMenuBar {

    Scene scene;

    MenuBar menuBar;
    Menu pageMenu;
    MenuItem loginMenuItem, registerMenuItem;

    public MenuBar createLoginMenuBar() {
        
        menuBar = new MenuBar();
        pageMenu = new Menu("Page");
        loginMenuItem = new MenuItem("Login");
        registerMenuItem = new MenuItem("Register");

        pageMenu.getItems().addAll(loginMenuItem, registerMenuItem);
        menuBar.getMenus().add(pageMenu);

        return menuBar;

    }

    public void setEventMenuBar(Stage primaryStage) {
        
        // login menu yg direct ke halaman login
        Login login = new Login();

        loginMenuItem.setOnAction(e -> {
            scene = login.show();
            primaryStage.setScene(scene);
            login.setEvent(primaryStage);
        });

        // register menu yg direct ke halaman register
        Register register = new Register();

        registerMenuItem.setOnAction(e -> {
            scene = register.show();
            primaryStage.setScene(scene);
            register.setEvent(primaryStage);
        });

    }

}
