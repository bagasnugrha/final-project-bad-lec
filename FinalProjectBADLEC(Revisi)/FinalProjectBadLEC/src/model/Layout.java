package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Layout {

    public BorderPane createBorderPane(MenuBar menuBar, GridPane content) {

        /* 
         * BorderPane ini jadi root
         * Parameter biar bisa langsung set MenuBar di bagian atas sama GridPane di bagian bawah
         **/

        BorderPane root = new BorderPane();

        root.setTop(menuBar);
        root.setCenter(content);

        return root;

    }

    public GridPane createGridPane() {

        GridPane grid = new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setAlignment(Pos.CENTER);

        return grid;

    }

}

