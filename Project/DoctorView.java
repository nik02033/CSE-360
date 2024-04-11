package Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DoctorView {
	String user;
	
	public DoctorView(Stage stage, String user) {
		// TODO Auto-generated constructor stub
		this.user =	user;
	}

	public void start(Stage stage){
		Text appointmentsButton = new Text("Appointments");
		
		String doctorInfoFile = user + "_DoctorInfo.txt";
		
		String name = "default name";
		
		try {
		    List<String> lines = Files.readAllLines(Paths.get(doctorInfoFile));
		    name = lines.get(0);
		   
		} catch (IOException e) {
		    // Handle the exception here. For example, log an error, show an error message, etc.
		    System.err.println("Error reading from the file: " + e.getMessage());
		    // Depending on your application, you might want to set a default name or take other actions
		}
		Text profileSettingsButton = new Text("Welcome Dr. " + name);			//refrencing to doctor
		
		appointmentsButton.setCursor(Cursor.HAND);
		appointmentsButton.setFont(Font.font("Century", 13));
		appointmentsButton.setOnMouseEntered(e -> appointmentsButton.setUnderline(true));
		appointmentsButton.setOnMouseExited(e -> appointmentsButton.setUnderline(false));

		profileSettingsButton.setFont(Font.font("Century", 13));
		
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
		

		Text healthSummaryTitle = new Text("Health Summary");
		
		Text messagesTitle = new Text("Messages");
		

		Text prescriptionsTitle = new Text("Prescriptions");
		
		Text healthTipsTitle = new Text("Health Tips/News");

		dashboardContent.getChildren().addAll(upcomingAppointmentsTitle, healthSummaryTitle, messagesTitle,
				prescriptionsTitle, healthTipsTitle);

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
}
