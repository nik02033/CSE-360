package Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Messaging {
    private String currentUser;

    
    public Messaging(Stage stage, String user) {
        currentUser = user;
        start(stage); // Make sure to call start to initialize the UI
    }

    private void appendMessageToFile(String filename, String message) {
        // Format the current date and time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);

        // Append the message to the file
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(timestamp + ": " + message + "\n");
        } catch (IOException e) {
            showAlert("Error", "Unable to write message to file.");
        }
    }

    private void startNewConversation(String patientUsername, ListView<String> conversationsList) {
        // File existence check and creation is handled similarly to before
        String file = patientUsername + "_PatientInfo.txt";
        if (!new File(file).exists()) {
            showAlert("Error", "Patient's username does not exist.");
            return;
        }

        String filename = patientUsername + "_msg_" + currentUser + ".txt";
        conversationsList.getItems().add("Chat with " + patientUsername); // Update conversation list UI
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        TextField searchField = new TextField();
        searchField.setPromptText("Enter username...");
        Button newConversationButton = new Button("+");
        HBox topPanel = new HBox(10, searchField, newConversationButton);
        topPanel.setPadding(new Insets(10));
        topPanel.setAlignment(Pos.CENTER_RIGHT);

        ListView<String> conversationsList = new ListView<>();
        root.setCenter(conversationsList);

        TextField messageInput = new TextField();
        Button sendButton = new Button("Send");
        HBox bottomPanel = new HBox(10, messageInput, sendButton);
        bottomPanel.setPadding(new Insets(10));
        bottomPanel.setAlignment(Pos.CENTER);

        newConversationButton.setOnAction(e -> startNewConversation(searchField.getText(), conversationsList));

        // Add functionality to send button for appending messages to file
        sendButton.setOnAction(e -> {
            if (!messageInput.getText().isEmpty() && !conversationsList.getSelectionModel().isEmpty()) {
                String selectedConversation = conversationsList.getSelectionModel().getSelectedItem();
                // Assuming the conversation format is "Chat with {username}"
                String patientUsername = selectedConversation.replace("Chat with ", "");
                String filename = patientUsername + "_msg_" + currentUser + ".txt";

                // Append message to file
                appendMessageToFile(filename, messageInput.getText());
                messageInput.clear(); // Clear the input field after sending
            }
        });

        root.setTop(topPanel);
        root.setBottom(bottomPanel);

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Messaging");
        stage.setScene(scene);
        stage.show();
    }
}
