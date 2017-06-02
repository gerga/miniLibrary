package ui;

import daos.ClientDao;
import domain.Client;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class ClientEditorController {
	@FXML
	private Button cancel_button;

	@FXML
	private TextField name_field;

	@FXML
	private TextField email_field;

	@FXML
	private TextField phone_field;
	
	private void clear(){
		this.name_field.setText("");
		this.email_field.setText("");
		this.phone_field.setText("");
	}
	
	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	void confirm(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("Cliente criado com sucesso!");
		// Client test
		System.out.println("=========================");
		System.out.println("CRIANDO CLIENTE");
		Client client = new Client(this.name_field.getText(), this.email_field.getText(), this.email_field.getText());
		ClientDao cd = new ClientDao();
		cd.insert(client);
		alert.showAndWait();
		clear();
	}
}
