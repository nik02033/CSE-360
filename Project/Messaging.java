package Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	private String currentUser = "";

	public Messaging(Stage stage, String user) {
		currentUser = user;
	}

	private void startNewConversation(String patientUsername, ListView<String> conversationsList) {
		// Check if patient info file exists
		String file = patientUsername + "_PatientInfo.txt";

//		Path patientInfoPath = Paths.get(patientUsername + "_PatientInfo.txt");
		if (!new File(file).exists()) {
			showAlert("Error", "Patient's username does not exist.");
			return;
		}

		// Proceed to check or create a message file
		String filename = patientUsername + "_msg_" + currentUser + ".txt";
		if (!new File(filename).exists()) {
			File msg = new File(filename);
		}

		conversationsList.getItems().add("Chat with " + patientUsername);

	}

	private void showAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void start(Stage stage) {
		// TODO Auto-generated constructor stub
		BorderPane root = new BorderPane();

		// Top: Search and New Conversation Button
		TextField searchField = new TextField();
		searchField.setPromptText("Enter username...");
		Button newConversationButton = new Button("+");
		HBox topPanel = new HBox(10, searchField, newConversationButton);
		topPanel.setPadding(new Insets(10));
		topPanel.setAlignment(Pos.CENTER_RIGHT);

		// Center: Conversation list or content
		ListView<String> conversationsList = new ListView<>();
		root.setCenter(conversationsList);

		// Bottom: Message input and send button
		TextField messageInput = new TextField();
		Button sendButton = new Button("Send");
		HBox bottomPanel = new HBox(10, messageInput, sendButton);
		bottomPanel.setPadding(new Insets(10));
		bottomPanel.setAlignment(Pos.CENTER);

		// Action for new conversation button
		newConversationButton.setOnAction(e -> startNewConversation(searchField.getText(), conversationsList));

		root.setTop(topPanel);
		root.setBottom(bottomPanel);

		Scene scene = new Scene(root, 400, 600);
		stage.setTitle("Messaging");
		stage.setScene(scene);
		stage.show();

	}
}