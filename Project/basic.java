package Project;

import javafx.application.Application;
import javafx.stage.Stage;


public class basic extends Application
{
	public void start(Stage stage) {
		SignIn obj = new SignIn(stage);
		obj.start(stage);
	}
	
	 public static void main(String[] args) {
	        launch(args);
	 }
}
