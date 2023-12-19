package controller;

import java.sql.SQLException;

import util.Connect;
import model.AppAlert;
import model.User;

public class LoginController {

    Connect connect = Connect.getInstance();

    AppAlert alert = new AppAlert();
    
    public static User user;

    public boolean loginValid(String userEmail, String userPassword) {

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
                String userId = connect.rs.getString("userID");
            	
            	System.out.println("User login successful.");
                alert.showInformationAlert("Success", "Login successful.");  

                user = new User();
                user.setUserID(userId);
                
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

    
