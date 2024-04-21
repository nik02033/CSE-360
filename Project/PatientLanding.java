package Project;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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

		profileSettingsButton.setCursor(Cursor.HAND);
		profileSettingsButton.setFont(Font.font("Century", 13));
		profileSettingsButton.setOnMouseEntered(e -> profileSettingsButton.setUnderline(true));
		profileSettingsButton.setOnMouseExited(e -> profileSettingsButton.setUnderline(false));

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

		Text upcomingAppointmentsTitle = new Text("Upcoming Appointments");
		upcomingAppointmentsTitle.setOnMouseClicked(e -> showAppointmentCreationForm(stage));
		
		upcomingAppointmentsTitle.setCursor(Cursor.HAND);
		upcomingAppointmentsTitle.setFont(Font.font("Century", 13));
		upcomingAppointmentsTitle.setOnMouseEntered(e -> upcomingAppointmentsTitle.setUnderline(true));
		upcomingAppointmentsTitle.setOnMouseExited(e -> upcomingAppointmentsTitle.setUnderline(false));
		
		Text messagesTitle = new Text("Messages");
		

		Text prescriptionsTitle = new Text("Prescriptions");
		
		
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
	
}
