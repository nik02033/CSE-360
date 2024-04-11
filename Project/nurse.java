package Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class nurse {
    String user;
	public void start(Stage primaryStage) {
        try {
            VBox root = new VBox(10);
            root.setPadding(new Insets(15));

            // Title label
            Label titleLabel = new Label("Patient Information");
            titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
            
            // Form for patient details
            GridPane form = new GridPane();
            form.setVgap(8);
            form.setHgap(10);

            // Labels
            Label nameLabel = new Label("Patient Name");
            Label heightLabel = new Label("Height");
            Label weightLabel = new Label("Weight");
            Label bloodPressureLabel = new Label("Blood Pressure");
            Label bodyTemperatureLabel = new Label("Body Temperature");
            Label allergiesLabel = new Label("Allergies");
            Label genderLabel = new Label("Gender");
            
            // Input fields
            TextField nameInput = new TextField();
            nameInput.setPromptText("Enter name");
            TextField heightInput = new TextField();
            heightInput.setPromptText("Enter height");
            TextField weightInput = new TextField();
            weightInput.setPromptText("Enter weight");
            TextField bloodPressureInput = new TextField();
            bloodPressureInput.setPromptText("Enter blood pressure");
            TextField bodyTemperatureInput = new TextField();
            bodyTemperatureInput.setPromptText("Enter body temperature");
            TextArea allergiesInput = new TextArea();
            allergiesInput.setPromptText("Enter allergies");
            allergiesInput.setPrefHeight(50);
            ComboBox<String> medicationsInput = new ComboBox<>();
            medicationsInput.setPromptText("Select Medications");

            // Gender RadioButtons
            ToggleGroup genderGroup = new ToggleGroup();
            RadioButton male = new RadioButton("Male");
            male.setToggleGroup(genderGroup);
            RadioButton female = new RadioButton("Female");
            female.setToggleGroup(genderGroup);
            RadioButton other = new RadioButton("Prefer not to say");
            other.setToggleGroup(genderGroup);
            male.setStyle("-fx-mark-color: purple;");
            female.setStyle("-fx-mark-color: purple;");
            other.setStyle("-fx-mark-color: purple;");

            // Insurance option
            Label insuranceLabel = new Label("Are you covered for your travels? "
            		+ "(add insurance 30$)");
            RadioButton insuranceYes = new RadioButton("Yes");
            RadioButton insuranceNo = new RadioButton("No, thanks");
            ToggleGroup insuranceGroup = new ToggleGroup();
            insuranceYes.setToggleGroup(insuranceGroup);
            insuranceNo.setToggleGroup(insuranceGroup);
            insuranceYes.setStyle("-fx-mark-color: purple;");
            insuranceNo.setStyle("-fx-mark-color: purple;");
            


            


            // Submit button
            Button submitButton = new Button("Continue");
            submitButton.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
            
            // Adding components to the form
            form.addRow(0, nameLabel, nameInput);
            form.addRow(1, heightLabel, heightInput);
            form.addRow(2, weightLabel, weightInput);
            form.addRow(3, bloodPressureLabel, bloodPressureInput);
            form.addRow(4, bodyTemperatureLabel, bodyTemperatureInput);
            form.addRow(5, allergiesLabel, allergiesInput);

            HBox genderBox = new HBox(10, genderLabel,male, female, other);
            HBox insuranceBox = new HBox(10, insuranceYes, insuranceNo);

            root.getChildren().addAll(titleLabel, form, genderBox, insuranceLabel, insuranceBox, submitButton);
            root.setAlignment(Pos.TOP_CENTER);
            
            submitButton.setOnAction(e -> {
                // Gather data from the form
                String patientName = nameInput.getText();
                String height = heightInput.getText();
                String weight = weightInput.getText();
                String bloodPressure = bloodPressureInput.getText();
                String bodyTemperature = bodyTemperatureInput.getText();
                String allergies = allergiesInput.getText();
                String gender = ((RadioButton) genderGroup.getSelectedToggle()).getText();
                String insurance = ((RadioButton) insuranceGroup.getSelectedToggle()).getText();

                // Create content for the file
                String content = "Patient Name: " + patientName + "\n" +
                                 "Height: " + height + "\n" +
                                 "Weight: " + weight + "\n" +
                                 "Blood Pressure: " + bloodPressure + "\n" +
                                 "Body Temperature: " + bodyTemperature + "\n" +
                                 "Allergies: " + allergies + "\n" +
                                 "Gender: " + gender + "\n" +
                                 "Insurance: " + insurance + "\n";

                // Generate a unique file name
                String fileName = "patient_" + patientName.replaceAll(" ", "_") + ".txt";

                // Save the content to the file
                try {
                    Files.write(Paths.get(fileName), content.getBytes(), StandardOpenOption.CREATE);
                    // Show a confirmation message or handle the saved data
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient information saved successfully!");
                    alert.showAndWait();
                } catch (IOException ioException) {
                    // Show an error message
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Error saving patient information.");
                    alert.showAndWait();
                    ioException.printStackTrace();
                }
            });


            Scene scene = new Scene(root, 600, 600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Medical Form");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public nurse (Stage stage,String user){
    	this.user =user;
	}

}

