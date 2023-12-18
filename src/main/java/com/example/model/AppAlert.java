package com.example.model;

import javafx.scene.control.Alert;

public class AppAlert {

    public void showInformationAlert(String title, String message) {

        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
