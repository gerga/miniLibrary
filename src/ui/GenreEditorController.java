package ui;

import java.io.IOException;

import daos.GenreDao;
import domain.Client;
import domain.Genre;
import domain.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GenreEditorController {

	@FXML
	private TextField name_field;

	@FXML
	private Button cancel_button;

	@FXML
	private Button confirm_button;

	private void clear(){
		this.name_field.setText("");
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
		alert.setContentText("Gênero criado com sucesso!");
		// Client test
		System.out.println("=========================");
		System.out.println("TESTAR GÊNERO");
		Genre genre = new Genre(this.name_field.getText());
		GenreDao gd = new GenreDao();
		gd.insert(genre);
		alert.showAndWait();
		clear();
	}
}
