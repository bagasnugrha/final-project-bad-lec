package view;

import java.sql.Date;

import controller.AppointmentListController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import model.AppMenuBar;
import model.AppointmentData;
import model.Layout;
import model.User;

public class AppointmentList {
	
	Scene scene;
	
	BorderPane root;
	GridPane grid;
	
	TableView<AppointmentData> appointmentTableView;
	
	TableColumn<AppointmentData, String> appointmentID;
	TableColumn<AppointmentData, String> doctorName;
	TableColumn<AppointmentData, Date> appointmentDate;
	TableColumn<AppointmentData, Float> timeSlot;
	
	MenuBar homeMenuBar;
	
	AppMenuBar menubar;
	
	Layout layout;
	
	AppointmentListController apController;
	
	User user;

	public void init() {
	    appointmentTableView = new TableView();
	    apController = new AppointmentListController();
	    
	    menubar = new AppMenuBar(); // Initialize menubar
	    
	    appointmentID = new TableColumn<>("Appointment ID");
	    doctorName = new TableColumn<>("Doctor Name");
	    appointmentDate = new TableColumn<>("Date");
	    timeSlot = new TableColumn<>("Time Slot");
	    
	    layout = new Layout();
	}

    
    public void set() {
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
		
		grid.setAlignment(Pos.CENTER);
		
		root = layout.createBorderPane(homeMenuBar, grid);
		
		scene = new Scene(root, 1048, 576);
    }
    
    public void setEvent(Stage primaryStage) {
    	 menubar.setEventMenuBar(primaryStage);
    }
    

    public Scene show() {
        init();
        set();  
        apController.getAppointmentData();
        
        return scene;
    }
}
