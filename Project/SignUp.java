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

public class SignUp {
	  public SignUp(Stage stage) {
		// TODO Auto-generated constructor stub
	}

	public void start(Stage stage) {
	        // Header
	        Text header = new Text("Sign up");
	        header.getStyleClass().add("header");

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
	        signUpButton.getStyleClass().add("sign-up-button");

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

	        HBox root = new HBox(20);
	        root.getChildren().add(vBox);
	        root.setAlignment(Pos.CENTER);
	        
	        // Apply CSS styling
	        Scene scene = new Scene(root, 800, 500);
	        scene.getStylesheets().add("style.css"); // Link to your CSS file

	        stage.setTitle("PediaEase");
	        stage.setScene(scene);
	        stage.show();
	    }
}
