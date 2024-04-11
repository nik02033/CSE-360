package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    public class mainui extends Application {

    	//Definition of views for different roles
        private ReceptionistView receptionistView;
        private TechnicianView technicianView;
        private PatientView patientView;
        
        public static void main(String[] args) {
            launch(args);
        }

        //Setup for the MainUI
        @Override
        public void start(Stage primaryStage) {
        	//Setting the Title
            primaryStage.setTitle("Heart Health Imaging and Recording System");

            receptionistView = new ReceptionistView();
            technicianView = new TechnicianView();
            patientView = new PatientView();
 
            //setting up the root layout
            VBox root = new VBox(40);
            root.setAlignment(Pos.CENTER);

            Label heading = new Label("Welcome to Heart Health Imaging and Recording System");

            // Create and add the buttons for different views
            Button patientIntakeButton = new Button("Patient Intake");
            patientIntakeButton.setPrefSize(160, 60);
            patientIntakeButton.setOnAction(event -> openNewWindow(new ReceptionistView(), "Patient Intake Form"));

            Button ctScanTechViewButton = new Button("CT Scan Tech View");
            ctScanTechViewButton.setPrefSize(160, 60);
            ctScanTechViewButton.setOnAction(event -> openNewWindow(new TechnicianView(), "Technician records"));

            Button patientViewButton = new Button("Patient View");
            patientViewButton.setPrefSize(160, 60);
            patientViewButton.setOnAction(event -> openNewWindow(new PatientView(), "Patient View"));

            root.getChildren().addAll(heading, patientIntakeButton, ctScanTechViewButton, patientViewButton);

            Scene scene = new Scene(root, 540, 480);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        
       //Method to open a new window with a given role based view
        private void openNewWindow(GridPane view, String title) {
            Stage stage = new Stage();
            stage.setScene(new Scene(view, 540, 480));
            stage.setTitle(title);
            stage.show();
        }

        //The methods for patient intake form which the Receptionist would fill
        public class ReceptionistView extends GridPane {
            private TextField firstNameField;
            private TextField lastNameField;
            private TextField emailField;
            private TextField phoneNumberField;
            private TextField healthHistoryField;
            private TextField insuranceIDField;
            private Button saveButton;
            
            //constructors to set up the interface
            public ReceptionistView() {
                setPadding(new Insets(10));
                setHgap(10);
                setVgap(10);

                //Add labels as the fillable grounds associated with text fields
                Label firstNameLabel = new Label("First Name:");
                Label lastNameLabel = new Label("Last Name:");
                Label emailLabel = new Label("Email:");
                Label phoneNumberLabel = new Label("Phone Number:");
                Label healthHistoryLabel = new Label("Health History:");
                Label insuranceIDLabel = new Label("Insurance ID:");

                firstNameField = new TextField();
                lastNameField = new TextField();
                emailField = new TextField();
                phoneNumberField = new TextField();
                healthHistoryField = new TextField();
                insuranceIDField = new TextField();
                saveButton = new Button("Save");

                add(firstNameLabel, 0, 0);
                add(firstNameField, 1, 0);
                add(lastNameLabel, 0, 1);
                add(lastNameField, 1, 1);
                add(emailLabel, 0, 2);
                add(emailField, 1, 2);
                add(phoneNumberLabel, 0, 3);
                add(phoneNumberField, 1, 3);
                add(healthHistoryLabel, 0, 4);
                add(healthHistoryField, 1, 4);
                add(insuranceIDLabel, 0, 5);
                add(insuranceIDField, 1, 5);
                add(saveButton, 2, 6);
                

                saveButton.setOnAction(event -> savePatientInformation());
            }

            //Methods to generate a unique patient ID and save the information associated to it
            private void savePatientInformation() {
                String patientID = generateUniquePatientID();
                String patientData = String.join("\n",
                        "First Name: " + firstNameField.getText(),
                        "Last Name: " + lastNameField.getText(),
                        "Email: " + emailField.getText(),
                        "Phone Number: " + phoneNumberField.getText(),
                        "Health History: " + healthHistoryField.getText(),
                        "Insurance ID: " + insuranceIDField.getText(),
                        "Patient ID: " + patientID
                );

                //methods to transfer patient data to a file
                try {
                    Files.write(Paths.get(patientID + "_PatientInfo.txt"), patientData.getBytes());
                    System.out.println("Patient information saved with ID: " + patientID);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //method to generate a unique patient ID
            private String generateUniquePatientID() {
                Random random = new Random();
                return String.format("%05d", random.nextInt(100000)); 
            }
        }
        
        //Technician's view to enter the data recorded after examining the patient
        public class TechnicianView extends GridPane {
            private TextField patientIDField;
            private TextField totalAgatStonCACScoreField;
            private TextField vesselLevelLMField;
            private TextField vesselLevelLADField;
            private TextField vesselLevelLCXField;
            private TextField vesselLevelRCAField;
            private TextField vesselLevelPDAField;
            private Button saveButton;

            // Constructor to set up the Technician's interface
            public TechnicianView() {
                setPadding(new Insets(10));
                setHgap(10);
                setVgap(10);

                //Add labels as the fillable grounds of data recorded by technician associated with text fields
                Label patientIDLabel = new Label("Patient ID:");
                Label totalAgatStonCACScoreLabel = new Label("The total Agatston CAC score:");
                Label vesselLevelLabel = new Label("Vessel level Agatston CAC score:");

                patientIDField = new TextField();
                totalAgatStonCACScoreField = new TextField();
                vesselLevelLMField = new TextField();
                vesselLevelLADField = new TextField();
                vesselLevelLCXField = new TextField();
                vesselLevelRCAField = new TextField();
                vesselLevelPDAField = new TextField();
                saveButton = new Button("Save");

                add(patientIDLabel, 0, 0);
                add(patientIDField, 1, 0);
                add(totalAgatStonCACScoreLabel, 0, 1);
                add(totalAgatStonCACScoreField, 1, 1);
                add(vesselLevelLabel, 0, 2);
                add(new Label("LM:"), 0, 3);
                add(vesselLevelLMField, 1, 3);
                add(new Label("LAD:"), 0, 4);
                add(vesselLevelLADField, 1, 4);
                add(new Label("LCX:"), 0, 5);
                add(vesselLevelLCXField, 1, 5);
                add(new Label("RCA:"), 0, 6);
                add(vesselLevelRCAField, 1, 6);
                add(new Label("PDA:"), 0, 7);
                add(vesselLevelPDAField, 1, 7);
                add(saveButton, 1, 8);
                

                saveButton.setOnAction(event -> saveCTScanData());
            }

            //Method to save the CTScan data
            private void saveCTScanData() {
                String patientID = patientIDField.getText();
                String ctScanData = String.join("\n",
                        "Patient ID: " + patientID,
                        "The total Agatston CAC score: " + totalAgatStonCACScoreField.getText(),
                        "LM: " + vesselLevelLMField.getText(),
                        "LAD: " + vesselLevelLADField.getText(),
                        "LCX: " + vesselLevelLCXField.getText(),
                        "RCA: " + vesselLevelRCAField.getText(),
                        "PDA: " + vesselLevelPDAField.getText()
                );

                //methods to transfer patient CTScan data recorded by technician to a file
                try {
                    Files.write(Paths.get(patientID + "CTResults.txt"), ctScanData.getBytes());
                    System.out.println("CT Scan data saved for patient ID: " + patientID);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
        //Method for the patient view so that patient can view their CTScan results
        public class PatientView extends GridPane {
            private TextField patientIDInputField;
            private Button loadButton;
            private Label patientNameLabel;
            private List<TextArea> ctScanResultsAreas;

            //Constructor to set up the Patient View interface
            public PatientView() {
                setPadding(new Insets(10));
                setHgap(10);
                setVgap(10);

                patientIDInputField = new TextField();
                loadButton = new Button("Search Patient Data");
                patientNameLabel = new Label();
                ctScanResultsAreas = new ArrayList<>();

                add(new Label("Enter Patient ID:"), 0, 0);
                add(patientIDInputField, 1, 0);
                add(loadButton, 2, 0);
                add(patientNameLabel, 0, 1, 2, 1);

                loadButton.setOnAction(event -> loadPatientData());
            }

            //Method to retrieve the patient CTScan data from the file
            private void loadPatientData() {
                String patientID = patientIDInputField.getText();
                try {
                    String patientInfo = new String(Files.readAllBytes(Paths.get(patientID + "_PatientInfo.txt")));
                    String ctScanResults = new String(Files.readAllBytes(Paths.get(patientID + "CTResults.txt")));
                    displayPatientData(patientInfo, ctScanResults);
                } 
                catch (IOException e) {
                    patientNameLabel.setText("Error: Could not load data for patient ID " + patientID);
                    e.printStackTrace();
                }
            }

            //Method to display patient data
            private void displayPatientData(String patientInfo, String ctScanResults) {
                
                String patientName = patientInfo.split("\n")[0].split(": ")[1]; 

              
                patientNameLabel.setText("Hello " + patientName);

                
                String[] results = ctScanResults.split("\n");

             
                for (TextArea area : ctScanResultsAreas) {
                    getChildren().remove(area);
                }
                
                ctScanResultsAreas.clear();

                //To Create a new TextArea for each piece of CTScan result and add it to the grid
                for (int i = 0; i < results.length; i++) {
                    TextArea area = new TextArea(results[i]);
                    area.setEditable(false);
                    ctScanResultsAreas.add(area);
                    add(area, 0, i + 3, 2, 1); 
                }
            }
        }
    }