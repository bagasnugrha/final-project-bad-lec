package view;

import java.time.LocalDate;
import java.time.ZoneId;

import controller.AppointmentFormController;
import controller.LoginController;
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
import model.AppMenuBar;
import model.Layout;
import model.User;

public class AppointmentForm {
	AppointmentFormController afController;
	User user;
	
	Scene scene;
	BorderPane root;
	GridPane grid;
	MenuBar homeMenuBar;	
	AppMenuBar menubar;
	Layout layout;
	
	//Content
	Label doctorNameLbl, dateLbl, timeSlotLbl;
	DatePicker dobPicker;
	ComboBox<String> doctorNameCB = new ComboBox<>();
	ComboBox<Float> timeSlotCB = new ComboBox<>();
	Button submitBtn;
	
	ObservableList<String> doctorNames;
	
	java.util.Date dateOfBirth;
	java.sql.Date sqlDob;
	
	public void init() {
		afController = new AppointmentFormController();

		doctorNames = afController.getDoctorNames();
		
		doctorNameLbl = new Label("Doctor Name");
		dateLbl = new Label("Date");
		timeSlotLbl = new Label("Time Slot"); 

		doctorNameCB.getItems().addAll(doctorNames);
		timeSlotCB.getItems().addAll(10.00f, 12.00f, 14.00f, 16.00f); 

		dobPicker = new DatePicker(LocalDate.now());
		
		submitBtn = new Button("Book Appointment");
		
		menubar = new AppMenuBar();
		layout = new Layout();
	}
	
	public void set() {
	    menubar = new AppMenuBar();
	    homeMenuBar = menubar.createHomeMenuBar();
	    grid = layout.createGridPane();

	    grid.add(doctorNameLbl, 0, 0);
	    grid.add(doctorNameCB, 0, 1);
	    grid.add(dateLbl, 0, 2);
	    grid.add(dobPicker, 0, 3);
	    grid.add(timeSlotLbl, 0, 4);
	    grid.add(timeSlotCB, 0, 5);
	    grid.add(submitBtn, 0, 6);

	    grid.setAlignment(Pos.CENTER);

	    root = layout.createBorderPane(homeMenuBar, grid);
	    scene = new Scene(root, 1048, 576);

	    dobPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
	        dateOfBirth = java.util.Date.from(newValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        sqlDob = new java.sql.Date(dateOfBirth.getTime());
	    });
	}
	
	public void setEvent(Stage primaryStage) {
        menubar.setEventMenuBarHome(primaryStage);
        
        user = LoginController.user;
        
        submitBtn.setOnAction(e -> {
        	System.out.println("Submit button pressed");
            String selectedDoctorName = doctorNameCB.getValue();
            Float selectedTimeSlot = timeSlotCB.getValue();
            String userID = user.getUserID();

            String doctorID = afController.getDoctorID(selectedDoctorName);

            afController.insertAppointmentToDatabase(sqlDob, userID, doctorID, selectedTimeSlot);

            System.out.println("Appointment is booked");
        });
    }
   

	public Scene show() {
       init();
       set();  
       afController.getDoctorNames();
       
       return scene;
	}
}
