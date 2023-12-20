package com.example.controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.ZoneId;

import com.example.model.AppAlert;
import com.example.util.Connect;
import com.example.view.Register;

import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

public class RegisterController {

    Connect connect = Connect.getInstance();
    AppAlert alert = new AppAlert();
    Register register = new Register();

    public boolean isRegisterValid(String userName, String userEmail, String userPassword, String confirmPassword, DatePicker dobPicker, String userGender, ToggleGroup genderGroup, String userPhoneNumber) {
        // Date parsing
        java.util.Date dateOfBirth = java.util.Date.from(dobPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDob = new java.sql.Date(dateOfBirth.getTime());


        // validasi input jangan ada yg kosong
        if(!isInputValid(userName, userEmail, userPassword, confirmPassword, sqlDob, genderGroup, userPhoneNumber)) {
            return false;
        }

        // validasi email jangan ada yg sama
        if(!isEmailValid(userEmail)) {
            return false;
        }

        // input datanya ke database
        inputNewUser(userName, userEmail, userPassword, sqlDob, userGender, userPhoneNumber);

        return true;
    }


    private boolean isEmailValid(String userEmail) {

        String query = "SELECT UserEmail FROM user WHERE UserEmail = '"+userEmail+"'";
        connect.execQuery(query);


        try {
            if (connect.rs.next()) { // kalo result set nemu email yg sama di database
                alert.showErrorAlert("Error", "Email has already been registered!");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

    private boolean isInputValid(String userName, String userEmail, String userPassword, String confirmPassword, Date userDateOfBirth, ToggleGroup genderGroup, String userPhoneNumber) {

        if (!userEmail.endsWith("@gmail.com")) {
            alert.showWarningAlert("Warning", "Email must end with '@gmail.com'.");
            return false;
        } else if (!(userPhoneNumber.startsWith("0")) || userPhoneNumber.isEmpty()) { // ! validasi nomor telpon
            alert.showWarningAlert("Warning", "Phone number must starts with 0.");
            return false;
        } else if (userName.isEmpty()) {
            alert.showWarningAlert("Warning", "Name must be filled.");
            return false;
        } else if (!(userPassword.length() >= 6)) {
            alert.showWarningAlert("Warning", "Password must contain at least 6 characters.");
            return false;
        } else if (!userPassword.equals(confirmPassword)) {
            alert.showWarningAlert("Warning", "Confirm Password must be the same as Password.");
            return false;
        } else if (genderGroup.getSelectedToggle() == null) {
            alert.showWarningAlert("Warning", "Gender must be selected.");
            return false;
        } else if (userDateOfBirth.equals(null)) {
            alert.showWarningAlert("Warning", "Date of birth must be filled.");
            return false;
        }

        return true;

    }

    private void inputNewUser(String userName, String userEmail, String userPassword, Date userDateOfBirth, String genderGroup, String userPhoneNumber) {

        String query = "INSERT INTO user (userID, userName, userEmail, userPassword, userDateOfBirth, userGender, userPhoneNumber) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement ps = connect.getPreparedStatement(query);

        try {
            ps.setString(1, generateUserID());
            ps.setString(2, userName);
            ps.setString(3, userEmail);
            ps.setString(4, userPassword);
            ps.setDate(5, userDateOfBirth);
            ps.setString(6, genderGroup);
            ps.setString(7, userPhoneNumber);
            ps.execute();
            alert.showInformationAlert("Success", "Account Created");
        } catch (SQLException e) {
            alert.showErrorAlert("Error", "Can not add new user to database");
            e.printStackTrace();
        }

        connect.closePreparedStatement();
        
    }


    private String generateUserID() {
        int userIndex = 0;

        String query = "SELECT MAX(CAST(SUBSTRING(UserID, 3) AS SIGNED)) AS maxIndex FROM user";
        connect.rs = connect.execQuery(query);

        try {
            if (connect.rs.next()) {
                int maxIndex = connect.rs.getInt("maxIndex");
                userIndex = maxIndex + 1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String indexStr = String.format("%03d", userIndex);

        return "US" + indexStr;

    }

}