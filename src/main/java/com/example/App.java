package com.example;

import java.io.IOException;

import com.example.view.Login;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Login login = new Login();

        primaryStage.setScene(login.show());
        login.setEvent(primaryStage);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
