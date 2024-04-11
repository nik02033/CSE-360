package Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private String text;
    private LocalDateTime timestamp;

    public Message(String text, LocalDateTime timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    public String getText() 
    {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // Format message for display
    public String getDisplayText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return formatter.format(timestamp) + " - " + text;
    }
}
