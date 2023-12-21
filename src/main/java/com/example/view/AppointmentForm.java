package com.example.view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import com.example.controller.AppointmentFormController;
import com.example.controller.LoginController;
import com.example.model.AppAlert;
import com.example.model.AppMenuBar;
import com.example.model.Layout;
import com.example.model.User;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AppointmentForm {
    
	AppointmentFormController afController;
	User user;
    AppAlert alert;
	
	Scene scene;
	BorderPane root;
	GridPane grid;
	MenuBar homeMenuBar;	
	AppMenuBar menubar;
	Layout layout;
	
	//Content
	Label doctorNameLbl, dateLbl, timeSlotLbl;
	DatePicker apDatePicker;
	ComboBox<String> doctorNameCB = new ComboBox<>();
	ComboBox<Float> timeSlotCB = new ComboBox<>();
	Button submitBtn;
	
	ObservableList<String> doctorNames;
	
	java.util.Date dateOfBirth;
	java.sql.Date sqlDob;
	
	private void init() {
		afController = new AppointmentFormController();
        alert = new AppAlert();

		doctorNames = afController.getDoctorNames();
		
		doctorNameLbl = new Label("Doctor Name");
		dateLbl = new Label("Date");
		timeSlotLbl = new Label("Time Slot"); 

		doctorNameCB.getItems().addAll(doctorNames);
		timeSlotCB.getItems().addAll(10.00f, 12.00f, 14.00f, 16.00f); 

		apDatePicker = new DatePicker(LocalDate.now());
		
		submitBtn = new Button("Book Appointment");
		
		menubar = new AppMenuBar();
		layout = new Layout();
	}
	
	private void set() {
	    menubar = new AppMenuBar();
	    homeMenuBar = menubar.createHomeMenuBar();
	    grid = layout.createGridPane();

	    grid.add(doctorNameLbl, 0, 0);
	    grid.add(doctorNameCB, 0, 1);
	    grid.add(dateLbl, 0, 2);
	    grid.add(apDatePicker, 0, 3);
	    grid.add(timeSlotLbl, 0, 4);
	    grid.add(timeSlotCB, 0, 5);
	    grid.add(submitBtn, 0, 6);

	    grid.setAlignment(Pos.CENTER);

	    root = layout.createBorderPane(homeMenuBar, grid);
	    scene = new Scene(root, 1048, 576);

        sqlDob = new Date(Calendar.getInstance().getTime().getTime());

	    apDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
	        dateOfBirth = java.util.Date.from(newValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        sqlDob = new java.sql.Date(dateOfBirth.getTime());
	    });
	}
	
	public void setEvent(Stage primaryStage) {
        // menubar
        menubar.setEventMenuBarHome(primaryStage);
        
        // ambil variabel user yg login
        user = LoginController.user;
        
        submitBtn.setOnAction(e -> {

            String selectedDoctorName = doctorNameCB.getValue();
            Float selectedTimeSlot = timeSlotCB.getValue();
            String userID = user.getUserID();
            String doctorID = afController.getDoctorID(selectedDoctorName);
            
            // cek inputnya valid atau ga
            if(!afController.isInputValid(selectedDoctorName, sqlDob, selectedTimeSlot)){
                alert.showErrorAlert("Error", "Please select all of the required data");
            } else {
                afController.insertAppointmentToDatabase(sqlDob, userID, doctorID, selectedTimeSlot);
                
                
                // direct ke halaman appointment list
            	AppointmentList appointmentList = new AppointmentList();
                scene = appointmentList.show();
                
                primaryStage.setTitle("Appointment List");
                primaryStage.setScene(scene);
                appointmentList.setEvent(primaryStage);
                // System.out.println("Appointment is booked");
            }

            // System.out.println("Submit button pressed");

        });
    }
   

	public Scene show() {
       init();
       set();  
       afController.getDoctorNames();
       
       return scene;
	}
}
