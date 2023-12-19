package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Homepage {
    Scene scene;

    Label text;
    GridPane root;
    Button homeBtn;


    public void init() {
        text = new Label("Page 2");
        root = new GridPane();
        homeBtn = new Button("Home");
    }
    
    public void set() {
        root.add(text, 0, 0);
        root.add(homeBtn, 0, 1);
        root.setAlignment(Pos.CENTER);
        scene = new Scene(root, 1366, 768);
    }
    
    public void setEvent(Stage primaryStage) {
    	homeBtn.setOnAction(e -> {
	        AppointmentList appointmentList = new AppointmentList();
	        scene = appointmentList.show();
	            
	        primaryStage.setTitle("Appointment List [User]");
	        primaryStage.setScene(scene);
	       
    	});
    }

    public Scene show() {
        init();
        set();
       
        return scene;
    }
}