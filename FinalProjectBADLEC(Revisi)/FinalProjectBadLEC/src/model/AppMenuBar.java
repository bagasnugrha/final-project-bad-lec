package model;

import view.AppointmentForm;
import view.AppointmentList;
import view.Login;
import view.Register;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class AppMenuBar {

    Scene scene;

    MenuBar menuBar;
    Menu pageMenu;
    MenuItem loginMenuItem, registerMenuItem, appointmentListItem, appointmentFormItem, logoutItem;

    Login login;

    public MenuBar createLoginMenuBar() {
        
        menuBar = new MenuBar();
        pageMenu = new Menu("Page");
        loginMenuItem = new MenuItem("Login");
        registerMenuItem = new MenuItem("Register");

        pageMenu.getItems().addAll(loginMenuItem, registerMenuItem);
        menuBar.getMenus().add(pageMenu);

        return menuBar;

    }

    public void setEventMenuBarLogin(Stage primaryStage) {
        
        // login menu yg direct ke halaman login
        login = new Login();

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

    public MenuBar createHomeMenuBar() {
    	
    	menuBar = new MenuBar();
    	pageMenu = new Menu("Home");
    	appointmentListItem = new MenuItem("Appointment List");
        appointmentFormItem = new MenuItem("Appointment Form");
        logoutItem = new MenuItem("Logout");
    	
    	pageMenu.getItems().addAll(appointmentListItem, appointmentFormItem, logoutItem);
    	menuBar.getMenus().add(pageMenu);
    	
		return menuBar;
    	
    }

    public void setEventMenuBarHome(Stage primaryStage) {
        AppointmentList appointmentList = new AppointmentList();
      
        appointmentListItem.setOnAction(e -> {
        	scene = appointmentList.show();
        	primaryStage.setScene(scene);
        	appointmentList.setEvent(primaryStage);

        });
        
        // ! uncomment pas appointment form udh jadi
         	AppointmentForm appointmentForm = new AppointmentForm();
        
         	appointmentFormItem.setOnAction(e -> {
         	scene = appointmentForm.show();
         	primaryStage.setScene(scene);
         	appointmentForm.setEvent(primaryStage);
        	
         });

        logoutItem.setOnAction(e -> {
            login = new Login();

            scene = login.show();
            primaryStage.setScene(scene);
            primaryStage.show();
            login.setEvent(primaryStage);
        });
    }

}