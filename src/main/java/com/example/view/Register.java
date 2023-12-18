package com.example.view;


import java.time.LocalDate;
import java.time.ZoneId;

import com.example.controller.RegisterController;
import com.example.model.AppMenuBar;
import com.example.model.Layout;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Register {

    Scene scene;

    MenuBar registerMenuBar;

    Label phoneLbl, emailLbl, nameLbl, passwordLbl, confirmPasswordLbl, genderLbl, dobLbl;

    TextField phoneField, emailField, nameField;
    PasswordField passwordField, confirmPasswordField;
    ToggleGroup genderGroup;
    RadioButton maleRb, femaleRb, elseRb;

    DatePicker dobPicker;
    Button registerBtn;

    BorderPane root;
    GridPane grid;

    // declare objek AppMenuBar
    AppMenuBar menubar;

    // declare objek Layout
    Layout layout;

    
    private void init() {

        // Labels
        phoneLbl = new Label("Phone Number");
        emailLbl = new Label("Email");
        passwordLbl = new Label("Password");
        nameLbl = new Label("Full Name");

        confirmPasswordLbl = new Label("Confirm Password");
        genderLbl = new Label("Gender");
        dobLbl = new Label("Date of Birth");

        // Field buat isi
        phoneField = new TextField();
        emailField = new TextField();
        passwordField = new PasswordField();
        confirmPasswordField = new PasswordField();
        nameField = new TextField();
        genderGroup = new ToggleGroup();
        maleRb = new RadioButton("Male");
        maleRb.setToggleGroup(genderGroup);
        femaleRb = new RadioButton("Female");
        femaleRb.setToggleGroup(genderGroup);
        elseRb = new RadioButton("Prefer not to Say");
        elseRb.setToggleGroup(genderGroup);

        dobPicker = new DatePicker(LocalDate.now()); // tambahin validasi buat jangan pilih tanggal sebelum hari ini

        // button
        registerBtn = new Button("Register");

        // inisialisasi objek AppMenuBar sama Layout
        menubar = new AppMenuBar();
        layout = new Layout();

    }

    private void set() {

        // ambil loginMenuBar dari class AppMenuBar
        registerMenuBar = menubar.createLoginMenuBar();

        // grid layouting
        grid = layout.createGridPane();
        grid.add(emailLbl, 0, 0);
        grid.add(emailField, 0, 1);
        grid.add(nameLbl, 0, 2);
        grid.add(nameField, 0, 3);
        grid.add(passwordLbl, 0, 4);
        grid.add(passwordField, 0, 5);
        grid.add(confirmPasswordLbl, 0, 6);
        grid.add(confirmPasswordField, 0, 7);
        grid.add(phoneLbl, 1, 0);
        grid.add(phoneField, 1, 1);
        grid.add(genderLbl, 1, 2);
        grid.add(maleRb, 1, 3);
        grid.add(femaleRb, 1, 4);
        grid.add(elseRb, 1, 5);
        grid.add(dobLbl, 1, 6);
        grid.add(dobPicker, 1, 7);
        
        grid.add(registerBtn, 0, 8);

        // isi root nya
        root = layout.createBorderPane(registerMenuBar, grid);
        
        // taro root ke scene
        scene = new Scene(root, 1048, 576);
    }

    public void setEvent(Stage primaryStage) {
        menubar.setEventMenuBar(primaryStage);
        // isi register controller

        registerBtn.setOnAction(e -> {
            RegisterController validate = new RegisterController();

            // Date parsing
            java.util.Date dateOfBirth = java.util.Date.from(dobPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDob = new java.sql.Date(dateOfBirth.getTime());

            System.out.println(genderGroup.getSelectedToggle()); // ngetes outputnya buat nanti dijadiin argumen di insert database

            // boolean isOk = validate.isInputValid(nameField.getText(), emailField.getText(), passwordField.getText(), confirmPasswordField.getText(), genderGroup, sqlDob, phoneField.getText())
            //                 && validate.isEmailValid(emailField.getText());

            // if (isOk) {
            //     // masukin data user dari textfield ke database
            //     validate.inputNewUser(nameField.getText(), emailField.getText(), passwordField.getText(), sqlDob, genderGroup, phoneField.getText());
            // }
                
        });
    }

    public Scene show() {
        init();
        set();

        return scene;
    }
}
