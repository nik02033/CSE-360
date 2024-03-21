package Project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
	        Text header1 = new Text("Sign Up to access PEDIAEASE");
	        HBox v = new HBox();
	        header.setFont(Font.font("System", FontWeight.BOLD, 30));
	        header1.setFont(Font.font("System", FontWeight.BOLD, 11.5));

	        
	        // Load the logo image
	        Image logoImage = new Image(getClass().getResourceAsStream("logo.png"));
	        ImageView logoImageView = new ImageView(logoImage);
	        logoImageView.setFitWidth(300); // Adjust the width as needed
	        logoImageView.setPreserveRatio(true);
	        
	        Image Image0 = new Image(getClass().getResourceAsStream("Image.png"));
	        ImageView Image1 = new ImageView(Image0);
	        Image1.setFitWidth(270); // Adjust the width as needed
	        Image1.setPreserveRatio(true);
	        
	        // Form fields
	        Text nameText = new Text("Name");
	        nameText.setFont(Font.font("System",FontWeight.BOLD,11.5));

	        TextField nameField = new TextField();
	        nameField.setPromptText("Enter your full name");
	        
	        Text emailText = new Text("Email Address");
	        emailText.setFont(Font.font("System",FontWeight.BOLD,11.5));
	        
	        TextField emailField = new TextField();
	        emailField.setPromptText("Enter your email address");

	        Text usernameText = new Text("Username");
	        usernameText.setFont(Font.font("System",FontWeight.BOLD,11.5));
	        TextField usernameField = new TextField();
	        usernameField.setPromptText("Create a username");
	        
	        Text passwordText = new Text("Password");
	        passwordText.setFont(Font.font("System",FontWeight.BOLD,11.5));
	        PasswordField passwordField = new PasswordField();
	        passwordField.setPromptText("Create a password");
	        
	        Text password1Text = new Text("Confirm Password");
	        password1Text.setFont(Font.font("System",FontWeight.BOLD,11.5));
	        PasswordField confirmPasswordField = new PasswordField();
	        confirmPasswordField.setPromptText("Confirm your password");

	        CheckBox termsCheckBox = new CheckBox("By clicking, you agree to the Terms and Privacy Policy.");
	        termsCheckBox.setCursor(Cursor.HAND);
	        
	        
	        Button signUpButton = new Button("Sign up");
	        signUpButton.setCursor(Cursor.HAND);
	        signUpButton.setFont(Font.font("Roboto", 16)); // Set font
	        signUpButton.setTextFill(Color.WHITE); // Set text color
	        signUpButton.setStyle("-fx-background-color: #45257d;");
	        signUpButton.setPrefWidth(350);
	        
	        // Layout
	        GridPane gridPane = new GridPane();
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setPadding(new Insets(6, 10, 10, 10));
	        gridPane.setVgap(10);
	        gridPane.setHgap(10);
	        
	        gridPane.add(nameText, 0, 0);
	        gridPane.add(nameField, 0, 1);
	        gridPane.add(emailText, 0, 2);
	        gridPane.add(emailField, 0, 3);
	        gridPane.add(usernameText, 0, 4);
	        gridPane.add(usernameField, 0, 5);
	        gridPane.add(passwordText, 0, 6);
	        gridPane.add(passwordField, 0, 7);
	        gridPane.add(password1Text, 0, 8);
	        gridPane.add(confirmPasswordField, 0, 9);
	        gridPane.add(termsCheckBox, 0, 10);
	        gridPane.add(signUpButton, 0, 11);
	        GridPane.setHalignment(signUpButton,HPos.CENTER);
	        GridPane.setHalignment(termsCheckBox,HPos.CENTER);

	        VBox vBox = new VBox(10);
	        vBox.getChildren().addAll(header, header1, gridPane);
	        vBox.setAlignment(Pos.CENTER);
	        
	        VBox vBox1 = new VBox();
	        vBox1.getChildren().addAll(logoImageView,Image1);
	        vBox1.setAlignment(Pos.CENTER_RIGHT);
	        vBox1.setSpacing(-20);
	        
	        v.setAlignment(Pos.CENTER);
	        v.setSpacing(100);
	        v.setPadding(new Insets(25,35,30,25));
	        v.getChildren().addAll(vBox1,vBox);
	        
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
