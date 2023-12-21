package com.example.view;

import java.sql.Date;

import com.example.controller.AppointmentListController;
import com.example.model.AppMenuBar;
import com.example.model.Appointment;
import com.example.model.Layout;
import com.example.model.User;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppointmentList {

    Scene scene;
	
	BorderPane root;
	GridPane grid;
	
	TableView<Appointment> appointmentTableView;
	
	TableColumn<Appointment, String> appointmentID;
	TableColumn<Appointment, String> doctorName;
	TableColumn<Appointment, Date> appointmentDate;
	TableColumn<Appointment, Float> timeSlot;
	
	MenuBar homeMenuBar;
	
	AppMenuBar menubar;
	
	Layout layout;
	
	AppointmentListController apController;
	
	User user;


    private void init() {
        appointmentTableView = new TableView<>();
	    apController = new AppointmentListController();
	    
	    menubar = new AppMenuBar(); // Initialize menubar
	    
	    appointmentID = new TableColumn<>("Appointment ID");
	    doctorName = new TableColumn<>("Doctor Name");
	    appointmentDate = new TableColumn<>("Date");
	    timeSlot = new TableColumn<>("Time Slot");
	    
	    layout = new Layout();
    }
    
    private void set() {
        menubar = new AppMenuBar();
    	homeMenuBar = menubar.createHomeMenuBar();
    	
		appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
		doctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
		appointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
		timeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));

		appointmentTableView.getColumns().clear();
		appointmentTableView.getColumns().addAll(appointmentID, doctorName, appointmentDate, timeSlot);
    	
		appointmentTableView.setItems(apController.getAppointmentList());
		
		appointmentTableView.setMaxSize(500, 500);
		appointmentTableView.setMinSize(360, 500);
		
		grid = layout.createGridPane();
		grid.add(appointmentTableView, 0, 0);
		
		root = layout.createBorderPane(homeMenuBar, grid);
		
		scene = new Scene(root, 1048, 576);
    }

    public void setEvent(Stage primaryStage) {
    	 menubar.setEventMenuBarHome(primaryStage);
    }

    public Scene show() {
        init();
        set();
        apController.getAppointmentData();
        return scene;
    }
}