package lib;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertLib {
	public void create_message(String title, String message, AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
}
