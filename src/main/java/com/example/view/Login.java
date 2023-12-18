package com.example.view;

import com.example.controller.LoginController;
import com.example.model.AppMenuBar;
import com.example.model.Layout;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Login {

    Scene scene;

    Label loginLbl,emailLbl, passwordLbl;
    TextField emailField;
    PasswordField passwordField;

    Button loginBtn;

    BorderPane root;
    GridPane grid;

    MenuBar loginMenuBar;

    // declare objek AppMenuBar
    AppMenuBar menubar;

    // declare objek Layout
    Layout layout;


    private void init() {
        
        loginLbl = new Label("Login");
        emailLbl = new Label("Email:");
        passwordLbl = new Label("Password:");

        emailField = new TextField();
        passwordField = new PasswordField();

        loginBtn = new Button("Login");

        menubar = new AppMenuBar();
        layout = new Layout();

    }

    private void set() {

        // ambil loginMenuBar dari class AppMenuBar
        loginMenuBar = menubar.createLoginMenuBar();

        /*
        * Ambil grid dari kelas Layout
        * Terus atur positioning
        **/
        grid = layout.createGridPane(); 
        grid.add(loginLbl, 0, 0, 2, 1);
        grid.add(emailLbl, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(passwordLbl, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(loginBtn, 1, 3);
        
        // ambil root (BorderPane) dari kelas Layout
        root = layout.createBorderPane(loginMenuBar, grid);

        // masukin root ke scene
        scene = new Scene(root, 1048, 576);

    }

    public void setEvent(Stage primaryStage) {
        menubar.setEventMenuBar(primaryStage);
        // isi controller login
        loginBtn.setOnAction(e -> {
            LoginController handleLogin = new LoginController();

            if(handleLogin.isLoginValid(emailField.getText(), passwordField.getText())) {
                Homepage homePage = new Homepage();
                scene = homePage.show();
                
                primaryStage.setTitle("Homepage");
                primaryStage.setScene(scene);
            }
        });
    }
   
    public Scene show() {
        init();
        set();

        return scene;
    }
}