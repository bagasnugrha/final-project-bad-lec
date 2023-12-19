module FinalProjectBadLEC {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	
	opens FinalProjectBADLEC;
	exports FinalProjectBADLEC;
	
	opens model to javafx.base;
	opens view to javafx.base;
	opens controller to javafx.base;
	opens util to java.sql;

}