package Project;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientLanding {
	String user;
	public PatientLanding(Stage stage,String user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	public void start(Stage stage) {
		Text appointmentsButton = new Text("Appointments");
		Text profileSettingsButton = new Text("Profile");

		appointmentsButton.setCursor(Cursor.HAND);
		appointmentsButton.setFont(Font.font("Century", 13));
		appointmentsButton.setOnMouseEntered(e -> appointmentsButton.setUnderline(true));
		appointmentsButton.setOnMouseExited(e -> appointmentsButton.setUnderline(false));
		appointmentsButton.setOnMouseClicked(event -> {
			displayAppointmentsForPatient(this.user);
		});
		profileSettingsButton.setCursor(Cursor.HAND);
		profileSettingsButton.setFont(Font.font("Century", 13));
		profileSettingsButton.setOnMouseEntered(e -> profileSettingsButton.setUnderline(true));
		profileSettingsButton.setOnMouseExited(e -> profileSettingsButton.setUnderline(false));
		profileSettingsButton.setOnMouseClicked(event -> {
			displayUserProfile(this.user);
		});

		Image msgImage = new Image(getClass().getResourceAsStream("msg.png"));
		ImageView msgImageView = new ImageView(msgImage);
		msgImageView.setFitWidth(35); // Adjust the width as needed
		msgImageView.setPreserveRatio(true);
		
		msgImageView.setCursor(Cursor.HAND);
		msgImageView.setOnMouseClicked(event -> {
			Messaging msg1 = new Messaging(stage,user); // Assuming SignUp has a similar constructor
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

		Text upcomingAppointmentsTitle = new Text("Schedule an Appointment");
		upcomingAppointmentsTitle.setOnMouseClicked(e -> showAppointmentCreationForm(stage));
		
		upcomingAppointmentsTitle.setCursor(Cursor.HAND);
		upcomingAppointmentsTitle.setFont(Font.font("Century", 13));
		upcomingAppointmentsTitle.setOnMouseEntered(e -> upcomingAppointmentsTitle.setUnderline(true));
		upcomingAppointmentsTitle.setOnMouseExited(e -> upcomingAppointmentsTitle.setUnderline(false));
		
		Text messagesTitle = new Text("Messages");
		

		Text prescriptionsTitle = new Text("Prescriptions");
		prescriptionsTitle.setOnMouseClicked(event -> {
			displayPatientNotes(stage);
		});
		
		dashboardContent.getChildren().addAll(upcomingAppointmentsTitle, messagesTitle,
				prescriptionsTitle);

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
	public void showAppointmentCreationForm(Stage parentStage) {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(parentStage);
        stage.setTitle("Schedule an Appointment");

        VBox layout = new VBox(10);

        // Doctor selection
        ComboBox<String> doctorSelection = new ComboBox<>();
        doctorSelection.setItems(FXCollections.observableArrayList("Dr. John Doe", "Dr. Sara Johnson", "Dr. Jessica Ship", "Dr Mathew Gray")); // Placeholder names
        doctorSelection.setPromptText("Select a Doctor");

        // Date picker
        DatePicker datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        datePicker.setPromptText("Select a Date");

        // Time selection (simplified as ComboBox for example purposes)
        ComboBox<String> timeSelection = new ComboBox<>();
        timeSelection.setItems(FXCollections.observableArrayList("09:00", "10:00", "11:00", "14:00", "15:00")); // Example times
        timeSelection.setPromptText("Select a Time");

        // Submit button
        Button submitButton = new Button("Schedule Appointment");
        submitButton.setOnAction(e -> {
            String doctoruser;
        	if(doctorSelection.getValue()=="Dr. John Doe") {
        		doctoruser = "JDoe";
            }
        	else if(doctorSelection.getValue()=="Dr. Sara Johnson") {
        		doctoruser = "SJohn";
            }
        	else if(doctorSelection.getValue()=="Dr. Jessica Ship") {
        		doctoruser = "JShip";
            }
        	else {
        		doctoruser = "MGray";
            }
        	String filename =  doctoruser + "_appointment_" + this.user + ".txt";
    		File file = new File(filename);
    		if (file.exists()) {
    			// Show an error popup because the username is taken
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Appointment Exists");
    			alert.setHeaderText(null);
    			alert.setContentText("Appointment Exists");
    			alert.showAndWait();
    			return; // Exit the method early
    		}
    		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
    			writer.write(datePicker.getValue().toString());
    			writer.newLine();
    			writer.write(timeSelection.getValue());
    			writer.newLine();

    		} catch (IOException ex) {
    			ex.printStackTrace();
    			// Here, handle the error, maybe show an error message dialog
    		}
    		
            stage.close();
        });

        layout.getChildren().addAll(new Label("Schedule a New Appointment"), doctorSelection, datePicker, timeSelection, submitButton);
        Scene scene = new Scene(layout, 300, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }
	private void displayAppointmentsForPatient(String patientUsername) {
	    Stage stage = new Stage(); // Create a new stage for displaying appointments
	    VBox layout = new VBox(10); // Vertical layout with spacing between nodes
	    layout.setStyle("-fx-padding: 10;"); // Padding around the VBox

	    File folder = new File("."); // Use the current directory; adjust if necessary
	    File[] listOfFiles = folder.listFiles();
	    boolean noAppointmentsFound = true;

	    if (listOfFiles != null) {
	        for (File file : listOfFiles) {
	            String filename = file.getName();
	            // Pattern to match files for a specific patient
	            if (filename.contains("_appointment_" + patientUsername + ".txt")) {
	                noAppointmentsFound = false; // Found at least one appointment file
	                try {
	                    List<String> lines = Files.readAllLines(Paths.get(filename));
	                    if (lines.size() >= 2) {
	                        String doctorUsername = filename.substring(0, filename.indexOf("_appointment_"));
	                        String date = lines.get(0);
	                        String time = lines.get(1);

	                        // Create a node for each appointment
	                        VBox appointmentNode = new VBox(5);
	                        Label appointmentInfo = new Label(String.format("Appointment with Dr. %s on %s at %s", doctorUsername, date, time));
	                        layout.getChildren().add(appointmentNode);
	                        appointmentNode.getChildren().add(appointmentInfo);
	                    }
	                } catch (IOException e) {
	                    showError("Failed to read appointment details from " + filename + ": " + e.getMessage());
	                }
	            }
	        }
	    }

	    // Check if no appointments were found
	    if (noAppointmentsFound) {
	        Label noAppointmentsLabel = new Label("No upcoming appointments for " + patientUsername);
	        layout.getChildren().add(noAppointmentsLabel);
	    }

	    Scene scene = new Scene(layout, 400, 300);
	    stage.setScene(scene);
	    stage.setTitle("Appointments for " + patientUsername);
	    stage.show();
	}

	private void showError(String message) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText(message);
	    alert.showAndWait();
	}
	public void displayUserProfile(String patientUsername) {
	    Stage stage = new Stage();
	    GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(20, 150, 10, 10));

	    // Read user info from files
	    String patientInfoFilename = patientUsername + "_PatientInfo.txt";
	    String patientDetailsFilename = "patient_" + patientUsername + ".txt";
	    TextField nameField = new TextField();
	    TextField emailField = new TextField();
	    TextField heightField = new TextField();
	    TextField weightField = new TextField();
	    TextField allergiesField = new TextField();

	    try {
	        List<String> patientInfoLines = Files.readAllLines(Paths.get(patientInfoFilename));
	        if (patientInfoLines.size() >= 2) {
	            nameField.setText(patientInfoLines.get(0));
	            emailField.setText(patientInfoLines.get(1));
	        }

	        List<String> patientDetailsLines = Files.readAllLines(Paths.get(patientDetailsFilename));
	        if (patientDetailsLines.size() >= 6) {
	            heightField.setText(patientDetailsLines.get(1));
	            weightField.setText(patientDetailsLines.get(2));
	            allergiesField.setText(patientDetailsLines.get(5));
	        }
	    } catch (IOException e) {
	        e.printStackTrace(); // Handle the exception properly
	    }

	    // Set fields uneditable initially
	    nameField.setEditable(false);
	    emailField.setEditable(false);
	    heightField.setEditable(false);
	    weightField.setEditable(false);
	    allergiesField.setEditable(false);

	    // Add components to grid
	    grid.add(new Label("Name:"), 0, 0);
	    grid.add(nameField, 1, 0);
	    grid.add(new Label("Email:"), 0, 1);
	    grid.add(emailField, 1, 1);
	    grid.add(new Label("Height:"), 0, 2);
	    grid.add(heightField, 1, 2);
	    grid.add(new Label("Weight:"), 0, 3);
	    grid.add(weightField, 1, 3);
	    grid.add(new Label("Allergies:"), 0, 4);
	    grid.add(allergiesField, 1, 4);

	    Button editButton = new Button("Edit");
	    Button saveButton = new Button("Save");
	    saveButton.setDisable(true); // Disabled until edit is clicked

	    // Edit button action
	    editButton.setOnAction(e -> {
	        nameField.setEditable(true);
	        emailField.setEditable(true);
	        heightField.setEditable(true);
	        weightField.setEditable(true);
	        allergiesField.setEditable(true);
	        saveButton.setDisable(false);
	    });

	    // Save button action
	    saveButton.setOnAction(e -> {
	        try {
	            // Save the patient info
	            List<String> patientInfo = List.of(nameField.getText(), emailField.getText());
	            Files.write(Paths.get(patientInfoFilename), patientInfo);
	            
	            // Save the patient details
	            List<String> patientDetails = Files.readAllLines(Paths.get(patientDetailsFilename));
	            patientDetails.set(1, heightField.getText()); // Update height
	            patientDetails.set(2, weightField.getText()); // Update weight
	            if (patientDetails.size() > 5) {
	                patientDetails.set(5, allergiesField.getText()); // Update allergies
	            }
	            Files.write(Paths.get(patientDetailsFilename), patientDetails);
	        } catch (IOException ex) {
	            ex.printStackTrace(); // Handle exception properly
	        }
	        saveButton.setDisable(true);
	        nameField.setEditable(false);
	        emailField.setEditable(false);
	        heightField.setEditable(false);
	        weightField.setEditable(false);
	        allergiesField.setEditable(false);
	    });

	    grid.add(editButton, 1, 5);
	    grid.add(saveButton, 2, 5);

	    Scene scene = new Scene(grid);
	    stage.setTitle("User Profile - " + patientUsername);
	    stage.setScene(scene);
	    stage.show();
	}
	public void displayPatientNotes( Stage stage) {
	    VBox layout = new VBox(10);
	    TextArea notesArea = new TextArea();
	    notesArea.setEditable(false); // Make the text area non-editable
	    
	    String patientUsername = this.user;
	    String notesFilename = patientUsername + "Notes.txt";
	    
	    // Read the notes from the file
	    String content = "";
	    try {
	        content = new String(Files.readAllBytes(Paths.get(notesFilename)));
	        notesArea.setText(content);
	    } catch (IOException e) {
	        notesArea.setText("No notes found for " + patientUsername);
	        // Handle exception as appropriate for your application
	        e.printStackTrace();
	    }

	    layout.getChildren().addAll(notesArea);

	    Scene scene = new Scene(layout, 600, 400);
	    stage.setTitle("Notes for " + patientUsername);
	    stage.setScene(scene);
	    stage.show();
	}
}
