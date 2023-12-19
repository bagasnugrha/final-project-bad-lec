package view;

import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.AppMenuBar;
import model.Layout;

public class AppointmentForm {
	Scene scene;
	
	BorderPane root;
	GridPane grid;
	
	MenuBar homeMenuBar;	
	AppMenuBar menubar;
	
	Layout layout;
	
	public void init() {
		 menubar = new AppMenuBar();
		 layout = new Layout();
	}
	
	public void set() {
		menubar = new AppMenuBar();
    	homeMenuBar = menubar.createHomeMenuBar();
    	grid = layout.createGridPane();

		
		root = layout.createBorderPane(homeMenuBar, grid);
		scene = new Scene(root, 1048, 576);
    	
	}
	
	public void setEvent(Stage primaryStage) {
		menubar.setEventMenuBar(primaryStage);
	}
   

	public Scene show() {
       init();
       set();  
       
       return scene;
	}
}
