package Project;


import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SignIn {
	public SignIn(Stage stage) {

	}
	
	public void start(Stage stage) {
        // Header
        Text header = new Text("Sign In");
        
        header.setFont(Font.font("System", FontWeight.BOLD, 30));
       
        
        HBox v = new HBox();
                
        // Load the logo image
        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(300); // Adjust the width as needed
        logoImageView.setPreserveRatio(true);
        
        Text emailText = new Text("Username");
        emailText.setFont(Font.font("System",FontWeight.BOLD,11.5));
       
        TextField emailField = new TextField();
        emailField.setPromptText("Enter your Username or Email");
        emailField.setPrefWidth(250);
        
        Text passwordText = new Text("Password");
        passwordText.setFont(Font.font("System", FontWeight.BOLD, 11.5));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        passwordField.setPrefWidth(250);
        
        Button signUpButton = new Button("Log In");
        signUpButton.setCursor(Cursor.HAND);
        signUpButton.setFont(Font.font("Roboto", 16)); // Set font
        signUpButton.setTextFill(Color.WHITE); // Set text color
        signUpButton.setStyle("-fx-background-color: #45257d;");
        signUpButton.setPrefWidth(250);
        Text signUpLink = new Text("New User ? SignUp instead");
        signUpLink.setFill(Color.BLACK); // Set the text color to blue
        signUpLink.setUnderline(true); // Underline the text
        signUpLink.setCursor(Cursor.HAND);
        
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
       
        gridPane.add(emailText, 0, 1); 
        gridPane.add(emailField, 0, 2); 
        gridPane.add(passwordText, 0, 3); 
        gridPane.add(passwordField, 0, 4);
        gridPane.add(signUpButton, 0, 7);
        gridPane.add(signUpLink, 0, 10);
        GridPane.setHalignment(signUpButton, HPos.CENTER);
        GridPane.setHalignment(signUpLink, HPos.CENTER);
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(header,gridPane);
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
