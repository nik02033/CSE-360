package Project;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PatientLanding {
	
	public PatientLanding(Stage stage) {
		// TODO Auto-generated constructor stub
	}
	
	public void start(Stage stage) {
	
		Button appointmentsButton = new Button("Appointments");
        Button profileSettingsButton = new Button("Profile Settings");
        Button messagingButton = new Button("Messaging");
        
        Image msgImage = new Image(getClass().getResourceAsStream("msg.png"));
		ImageView msgImageView = new ImageView(msgImage);
		msgImageView.setFitWidth(35); // Adjust the width as needed
		msgImageView.setPreserveRatio(true);
        
        // Create HBox for left-aligned items
        HBox leftItems = new HBox(appointmentsButton);
        
        
        HBox.setHgrow(leftItems, javafx.scene.layout.Priority.ALWAYS);
        leftItems.setAlignment(Pos.CENTER_LEFT);

        // Create HBox for right-aligned items
        HBox rightItems = new HBox(msgImageView,profileSettingsButton);
        rightItems.setAlignment(Pos.CENTER_RIGHT);

        // Create main HBox and add left and right items
        HBox navbar = new HBox(leftItems, rightItems);
        // Set spacing between elements
        navbar.setSpacing(20);
        // Align the main HBox content
        navbar.setAlignment(Pos.TOP_CENTER);
		
		Scene scene = new Scene(navbar, 800, 500);

		stage.setTitle("PediaEase");
		stage.setScene(scene);
		stage.show();
	}
}
