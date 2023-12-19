package model;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import view.AppointmentForm;
import view.AppointmentList;
import view.Login;
import view.Register;

public class AppMenuBar {

    Scene scene;

    MenuBar menuBar;
    Menu pageMenu;
    MenuItem loginMenuItem, registerMenuItem, appointmentListItem, appointmentFormItem;

    public MenuBar createLoginMenuBar() {
        
        menuBar = new MenuBar();
        pageMenu = new Menu("Page");
        loginMenuItem = new MenuItem("Login");
        registerMenuItem = new MenuItem("Register");

        pageMenu.getItems().addAll(loginMenuItem, registerMenuItem);
        menuBar.getMenus().add(pageMenu);

        return menuBar;

    }
    

    public MenuBar createHomeMenuBar() {
    	
    	menuBar = new MenuBar();
    	pageMenu = new Menu("Home");
    	appointmentListItem = new MenuItem("Appointment List");
        appointmentFormItem = new MenuItem("Appointment Form");
    	
    	pageMenu.getItems().addAll(appointmentListItem, appointmentFormItem);
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
        
        AppointmentList appointmentList = new AppointmentList();
      
        appointmentListItem.setOnAction(e -> {
        	scene = appointmentList.show();
        	primaryStage.setScene(scene);
        	appointmentList.setEvent(primaryStage);
        	
        });
        
        AppointmentForm appointmentForm = new AppointmentForm();
        
        appointmentFormItem.setOnAction(e -> {
        	scene = appointmentForm.show();
        	primaryStage.setScene(scene);
        	appointmentForm.setEvent(primaryStage);
        	
        });
        

    }
}
