package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DoctorView {
	String user;

	public DoctorView(Stage stage, String user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	public void start(Stage stage) {
		Text appointmentsButton = new Text("Appointments");

		String doctorInfoFile = user + "_DoctorInfo.txt";

		String name = "default name";

		try {
			List<String> lines = Files.readAllLines(Paths.get(doctorInfoFile));
			name = lines.get(0);

		} catch (IOException e) {
			// Handle the exception here. For example, log an error, show an error message,
			// etc.
			System.err.println("Error reading from the file: " + e.getMessage());
			// Depending on your application, you might want to set a default name or take
			// other actions
		}
		Text profileSettingsButton = new Text("Welcome Dr. " + name); // refrencing to doctor

		appointmentsButton.setCursor(Cursor.HAND);
		appointmentsButton.setFont(Font.font("Century", 13));
		appointmentsButton.setOnMouseEntered(e -> appointmentsButton.setUnderline(true));
		appointmentsButton.setOnMouseExited(e -> appointmentsButton.setUnderline(false));

		appointmentsButton.setOnMouseClicked(event -> {
			displayAllAppointmentsForDoctor(this.user);
		});

		profileSettingsButton.setFont(Font.font("Century", 13));

		Image msgImage = new Image(getClass().getResourceAsStream("msg.png"));
		ImageView msgImageView = new ImageView(msgImage);
		msgImageView.setFitWidth(35); // Adjust the width as needed
		msgImageView.setPreserveRatio(true);

		msgImageView.setCursor(Cursor.HAND);

		msgImageView.setOnMouseClicked(event -> {
			Messaging msg1 = new Messaging(stage, user); // Assuming SignUp has a similar constructor
			msg1.start(stage); // Call the start method of SignUp, passing the current stage

		});

		Image logoImage = new Image(getClass().getResourceAsStream("logo1.png"));
		ImageView logoImageView = new ImageView(logoImage);
		logoImageView.setFitWidth(120); // Adjust the width as needed
		logoImageView.setPreserveRatio(true);

		// Create HBox for left-aligned items
		HBox leftItems = new HBox(appointmentsButton);
		leftItems.setAlignment(Pos.CENTER_LEFT);

		// Create HBox for right-aligned items including the messaging icon and button
		HBox rightItems = new HBox(msgImageView, profileSettingsButton); // Adjust the order if needed
		rightItems.setAlignment(Pos.CENTER_RIGHT);
		rightItems.setSpacing(10); // Add some spacing between items

		// Create main HBox for navbar and set its background to purple
		HBox navbar = new HBox(logoImageView, leftItems, rightItems);
		navbar.setSpacing(20); // Space between left and right items
		navbar.setAlignment(Pos.CENTER);
		navbar.setStyle("-fx-background-color: #c7b8d6;"); // Set navbar background color
		navbar.setPrefHeight(50); // Set navbar height

		VBox dashboardContent = new VBox(10);
		dashboardContent.setPadding(new Insets(15));

		Text upcomingAppointmentsTitle = new Text("Upcoming Appointments");

		Text messagesTitle = new Text("Messages");

		dashboardContent.getChildren().addAll(upcomingAppointmentsTitle, messagesTitle);

		BorderPane mainLayout = new BorderPane();

		mainLayout.setTop(navbar);
		mainLayout.setCenter(dashboardContent);

		// Ensure the left and right items are visually separated and occupy the
		// intended spaces
		BorderPane.setAlignment(leftItems, Pos.CENTER_LEFT);
		BorderPane.setAlignment(rightItems, Pos.CENTER_RIGHT);

		Scene scene = new Scene(mainLayout, 800, 500);

		stage.setTitle("PediaEase");
		stage.setScene(scene);
		stage.show();
	}
//	public void displayAllAppointmentsForDoctor(String doctorUsername) {
//	    File folder = new File("."); // Use the current directory; adjust if necessary
//	    File[] listOfFiles = folder.listFiles();
//
//	    boolean appointmentsFound = false;
//
//	    if (listOfFiles != null) {
//	        for (File file : listOfFiles) {
//	            String filename = file.getName();
//	            // Check if the filename starts with the doctor's username followed by "_appointment"
//	            if (filename.startsWith(doctorUsername + "_appointment") && filename.endsWith(".txt")) {
//	                appointmentsFound = true;
//	                try {
//	                    // Read all lines from the file
//	                    List<String> lines = Files.readAllLines(Paths.get(filename));
//	                    if (lines.size() >= 2) {
//	                        String patientUsername = filename.substring((doctorUsername + "_appointment_").length(), filename.length() - 4);
//	                        String date = lines.get(0);  // First line is the date
//	                        String time = lines.get(1);  // Second line is the time
//
//	                        // Display the appointment details
//	                        displayAppointmentInfo(doctorUsername, patientUsername, date, time);
//	                    } else {
//	                        showError("Appointment data is incomplete in " + filename + ".");
//	                    }
//	                } catch (IOException e) {
//	                    showError("Failed to read appointment details from " + filename + ": " + e.getMessage());
//	                }
//	            }
//	        }
//	        if (!appointmentsFound) {
//	            showError("No appointment details found for Dr. " + doctorUsername);
//	        }
//	    }
//	}
	private void displayAllAppointmentsForDoctor(String doctorUsername) {
		Stage stage = new Stage(); // Create a new stage for displaying appointments
	    VBox layout = new VBox(10); // Vertical layout with spacing between nodes
	    layout.setStyle("-fx-padding: 10;"); // Padding around the VBox

	    File folder = new File("."); // Use the current directory; adjust if necessary
	    File[] listOfFiles = folder.listFiles();
	    boolean noAppointmentsFound = true;

	    if (listOfFiles != null) {
	        for (File file : listOfFiles) {
	            String filename = file.getName();
	            if (filename.startsWith(doctorUsername + "_appointment") && filename.endsWith(".txt")) {
	                noAppointmentsFound = false; // Found at least one appointment file
	                try {
	                    List<String> lines = Files.readAllLines(Paths.get(filename));
	                    if (lines.size() >= 2) {
	                        String patientUsername = filename.substring((doctorUsername + "_appointment_").length(), filename.length() - 4);
	                        String date = lines.get(0);
	                        String time = lines.get(1);

	                        // Create a node for each appointment
	                        VBox appointmentNode = new VBox(5);
	                        Label appointmentInfo = new Label(String.format("Patient %s: %s at %s", patientUsername, date, time));
	                        Button deleteButton = new Button("Delete");
	                        deleteButton.setOnAction(e -> {
	                            file.delete(); // Delete the file
	                            layout.getChildren().remove(appointmentNode); // Remove the node from the layout
	                        });

	                        appointmentNode.getChildren().addAll(appointmentInfo, deleteButton);
	                        layout.getChildren().add(appointmentNode);
	                    }
	                } catch (IOException e) {
	                    showError("Failed to read appointment details from " + filename + ": " + e.getMessage());
	                }
	            }
	        }
	    }

	    // Check if no appointments were found
	    if (noAppointmentsFound) {
	        Label noAppointmentsLabel = new Label("No upcoming appointments for Dr. " + doctorUsername);
	        layout.getChildren().add(noAppointmentsLabel);
	    }

	    Scene scene = new Scene(layout, 400, 300);
	    stage.setScene(scene);
	    stage.setTitle("Appointments for Dr. " + doctorUsername);
	    stage.show();
	}

	private void showError(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}

}
