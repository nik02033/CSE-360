package Project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SignIn {
	public SignIn(Stage stage) {
		// TODO Auto-generated constructor stub
	}
	public void start(Stage stage) {
        // Header
        Text header = new Text("Sign up");
        header.getStyleClass().add("header");

        
        // Load the logo image
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200); // Adjust the width as needed
        logoImageView.setPreserveRatio(true);
        
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your Username or Email");
        emailField.setPrefWidth(250);
        
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPrefWidth(250);
        
        
        Button signUpButton = new Button("Log In");
        signUpButton.getStyleClass().add("sign-up-button");
        
        Text signUpLink = new Text("New User? Sign Up");
        signUpLink.getStyleClass().add("hyperlink-style"); // Add this style class in your CSS file to style the text like a hyperlink
        signUpLink.setOnMouseClicked(event -> {
            SignUp signUpScreen = new SignUp(stage); // Assuming SignUp has a similar constructor
            signUpScreen.start(stage); // Call the start method of SignUp, passing the current stage
        });
        
        
        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        
        
        
       
        gridPane.add(emailField, 0, 0); 
        gridPane.add(passwordField, 0, 1);
        gridPane.add(signUpButton, 0, 2);
        gridPane.add(signUpLink, 0, 3);
        GridPane.setHalignment(signUpButton, HPos.CENTER);
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(logoImageView,header, gridPane);
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
