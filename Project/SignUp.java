package Project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SignUp {
	  public SignUp(Stage stage) {
		// TODO Auto-generated constructor stub
	}

	public void start(Stage stage) {
	        // Header
	        Text header = new Text("Sign up");
	        HBox v = new HBox();

	        
	        // Load the logo image
	        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
	        ImageView logoImageView = new ImageView(logoImage);
	        logoImageView.setFitWidth(250); // Adjust the width as needed
	        logoImageView.setPreserveRatio(true);
	        
	        
	        // Form fields
	        TextField nameField = new TextField();
	        nameField.setPromptText("Enter your full name");

	        TextField emailField = new TextField();
	        emailField.setPromptText("Enter your email address");

	        TextField usernameField = new TextField();
	        usernameField.setPromptText("Create a username");

	        PasswordField passwordField = new PasswordField();
	        passwordField.setPromptText("Create a password");

	        PasswordField confirmPasswordField = new PasswordField();
	        confirmPasswordField.setPromptText("Confirm your password");

	        CheckBox termsCheckBox = new CheckBox("By signing up, you agree to the Terms and Privacy Policy.");

	        Button signUpButton = new Button("Sign up");
	       
	        // Layout
	        GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setPadding(new Insets(10, 10, 10, 10));
	        gridPane.setVgap(10);
	        gridPane.setHgap(10);

	        gridPane.add(nameField, 0, 0);
	        gridPane.add(emailField, 0, 1);
	        gridPane.add(usernameField, 0, 2);
	        gridPane.add(passwordField, 0, 3);
	        gridPane.add(confirmPasswordField, 0, 4);
	        gridPane.add(termsCheckBox, 0, 5);
	        gridPane.add(signUpButton, 0, 6);

	        VBox vBox = new VBox(10);
	        vBox.getChildren().addAll(header, gridPane);
	        vBox.setAlignment(Pos.CENTER);
	        v.setAlignment(Pos.CENTER_LEFT);
	        v.setSpacing(100);
	        v.getChildren().addAll(logoImageView,vBox);
	        
	        HBox root = new HBox(20);
	        root.getChildren().add(v);
	        root.setAlignment(Pos.CENTER);
	        
	        // Apply CSS styling
	        Scene scene = new Scene(root, 800, 500);
	      
	        stage.setTitle("PediaEase");
	        stage.setScene(scene);
	        stage.show();
	    }
}
