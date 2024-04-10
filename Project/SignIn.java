package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
		logoImageView.setFitWidth(320); // Adjust the width as needed
		logoImageView.setPreserveRatio(true);

		Image Image0 = new Image(getClass().getResourceAsStream("Image.png"));
		ImageView Image1 = new ImageView(Image0);
		Image1.setFitWidth(280); // Adjust the width as needed
		Image1.setPreserveRatio(true);

		Text emailText = new Text("Username");
		emailText.setFont(Font.font("System", FontWeight.BOLD, 11.5));

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
		Text signUpLink = new Text("New User? Sign Up instead");
		signUpLink.setFill(Color.BLACK); // Set the text color to blue
		signUpLink.setOnMouseEntered(e -> signUpLink.setUnderline(true));
		signUpLink.setOnMouseExited(e -> signUpLink.setUnderline(false));

		signUpLink.setCursor(Cursor.HAND);

		signUpLink.setOnMouseClicked(event -> {
			SignUp signUpScreen = new SignUp(stage); // Assuming SignUp has a similar constructor
			signUpScreen.start(stage); // Call the start method of SignUp, passing the current stage

		});
		
		signUpButton.setOnAction(event -> {
			readPatientData(emailField,passwordField,stage);
			
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

		vBox.getChildren().addAll(header, gridPane);
		vBox.setAlignment(Pos.CENTER);

		VBox vBox1 = new VBox();
		vBox1.getChildren().addAll(logoImageView, Image1);
		vBox1.setAlignment(Pos.CENTER);
		vBox1.setSpacing(-50);

		v.setAlignment(Pos.CENTER_LEFT);
		v.setSpacing(100);
		v.getChildren().addAll(vBox1, vBox);

		HBox root = new HBox(20);
		root.getChildren().add(v);
		root.setAlignment(Pos.CENTER);

		// Apply CSS styling
		Scene scene = new Scene(root, 800, 500);

		stage.setTitle("PediaEase");
		stage.setScene(scene);
		stage.show();
	}

	private void readPatientData(TextField username, TextField password, Stage stage) {
		try {
			String patientInfoFilename =  username.getText() + "_PatientInfo.txt";
			if (new File(patientInfoFilename).exists()) {
				List<String> lines = Files.readAllLines(Paths.get(patientInfoFilename));
				if(lines.get(3).equals(password.getText())) {
					Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
					confirmAlert.setTitle("LogIn Successful");
					confirmAlert.setHeaderText(null);
					confirmAlert.setContentText("You have successfully logged in. Proceeding to the next step.");
					confirmAlert.showAndWait();
					Platform.runLater(() -> {
						PatientLanding patientPage = new PatientLanding(stage);
						patientPage.start(stage);
					});
				}
				else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Incorrect Details");
					alert.setHeaderText(null);
					alert.setContentText("The username or password is/are incorrect");
					alert.showAndWait();
					return;
				}
			}
			else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Incorrect Details");
				alert.setHeaderText(null);
				alert.setContentText("The username or password is/are incorrect");
				alert.showAndWait();
				return;
			}
			// Read the CT results from the file.
			
			
		} catch (IOException e) {
			e.printStackTrace();

		}
	}
}
