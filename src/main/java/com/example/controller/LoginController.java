package com.example.controller;

import java.sql.SQLException;

import com.example.util.Connect;
import com.example.model.AppAlert;

public class LoginController {

    Connect connect = Connect.getInstance();
    AppAlert alert = new AppAlert();

    public boolean isLoginValid(String userEmail, String userPassword) {

        // Validate username and password
        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            alert.showInformationAlert("Warning", "Username and password must be filled.");
            return false;
        }

        String query = "SELECT * FROM user WHERE userEmail = ? AND userPassword = ?";

        try {
            connect.ps = connect.getPreparedStatement(query);
            connect.ps.setString(1, userEmail);
            connect.ps.setString(2, userPassword);
            connect.rs = connect.ps.executeQuery();

            if (connect.rs.next()) {
                System.out.println("User login successful.");
                alert.showInformationAlert("Success", "Login successful.");  

                return true;
            } else {
                alert.showInformationAlert("Error", "Invalid username or password. Please try again.");
            }

        } catch (SQLException ex) {
            alert.showInformationAlert("Error", "Database connection error.");
            ex.printStackTrace();
        } finally {
            connect.closePreparedStatement();
        }

        return false;

    }
}

    
