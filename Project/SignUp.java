package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Platform;
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
import javafx.scene.control.Alert;

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
		nameText.setFont(Font.font("System", FontWeight.BOLD, 11.5));

		TextField nameField = new TextField();
		nameField.setPromptText("Enter your full name");

		Text emailText = new Text("Email Address");
		emailText.setFont(Font.font("System", FontWeight.BOLD, 11.5));

		TextField emailField = new TextField();
		emailField.setPromptText("Enter your email address");

		Text usernameText = new Text("Username");
		usernameText.setFont(Font.font("System", FontWeight.BOLD, 11.5));
		TextField usernameField = new TextField();
		usernameField.setPromptText("Create a username");

		Text passwordText = new Text("Password");
		passwordText.setFont(Font.font("System", FontWeight.BOLD, 11.5));
		PasswordField passwordField = new PasswordField();
		passwordField.setPromptText("Create a password");

		Text password1Text = new Text("Confirm Password");
		password1Text.setFont(Font.font("System", FontWeight.BOLD, 11.5));
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
		GridPane.setHalignment(signUpButton, HPos.CENTER);
		GridPane.setHalignment(termsCheckBox, HPos.CENTER);

		VBox vBox = new VBox(10);
		vBox.getChildren().addAll(header, header1, gridPane);
		vBox.setAlignment(Pos.CENTER);

		VBox vBox1 = new VBox();
		vBox1.getChildren().addAll(logoImageView, Image1);
		vBox1.setAlignment(Pos.CENTER_RIGHT);
		vBox1.setSpacing(-20);

		v.setAlignment(Pos.CENTER);
		v.setSpacing(100);
		v.setPadding(new Insets(25, 35, 30, 25));
		v.getChildren().addAll(vBox1, vBox);

		HBox root = new HBox(20);
		root.getChildren().add(v);
		root.setAlignment(Pos.CENTER);

		signUpButton.setOnAction(event -> {
			if (allFieldsAreFilled(nameField, emailField, usernameField, passwordField, confirmPasswordField)) {
				String password1 = passwordField.getText();
				String password2 = confirmPasswordField.getText();
				if (!(password1.equals(password2))) {
					// Show a popup alert if the passwords do not match
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Password Error");
					alert.setHeaderText(null);
					alert.setContentText("Passwords do not match!");
					alert.showAndWait();
				} else {
					savePatientInformation(nameField, emailField, usernameField, passwordField, stage);
				}
			} else {
				// Show an error message to the technician.
				System.err.println("All fields are required. Please fill in all fields before saving.");
			}
		});

		// Apply CSS styling
		Scene scene = new Scene(root, 800, 500);

		stage.setTitle("PediaEase");
		stage.setScene(scene);
		stage.show();
	}

	private void savePatientInformation(TextField nameField, TextField emailField, TextField usernameField,
			PasswordField passwordField, Stage stage) {
		String filename = usernameField.getText() + "_PatientInfo.txt";
		File file = new File(filename);

		// Check if the file already exists
		if (file.exists()) {
			// Show an error popup because the username is taken
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Username Taken");
			alert.setHeaderText(null);
			alert.setContentText("The username is already taken. Please choose a different one.");
			alert.showAndWait();
			return; // Exit the method early
		}

		// Write contents into file
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(nameField.getText());
			writer.newLine();
			writer.write(emailField.getText());
			writer.newLine();
			writer.write(usernameField.getText());
			writer.newLine();
			writer.write(passwordField.getText());
			writer.newLine();

			Alert confirmAlert = new Alert(Alert.AlertType.INFORMATION);
			confirmAlert.setTitle("Registration Successful");
			confirmAlert.setHeaderText(null);
			confirmAlert.setContentText("Patient information has been successfully saved. Proceeding to the next step.");
			confirmAlert.showAndWait();

			Platform.runLater(() -> {
				// Proceed to next stage after confirmation
				SignIn signInScreen = new SignIn(stage); // Assuming SignUp has a similar constructor
				signInScreen.start(stage);
			});

		} catch (IOException ex) {
			ex.printStackTrace();
			// Here, handle the error, maybe show an error message dialog
		}
	}

	private boolean allFieldsAreFilled(TextField... fields) {
		for (TextField field : fields) {
			if (field.getText().trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}

}
