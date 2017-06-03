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
import lib.AlertLib;

public class GenreEditorController {

	@FXML
	private TextField name_field;

	@FXML
	private Button cancel_button;

	@FXML
	private Button confirm_button;

	private void clear() {
		this.name_field.setText("");
	}

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	private boolean check_fields() {
		if (this.name_field.getText().isEmpty()) {
			AlertLib a = new AlertLib();
			a.create_message("Erro", "Nome não pode ser vazio", AlertType.WARNING);
			return false;
		}
		return true;
	}

	@FXML
	void confirm(ActionEvent event) {
		if (check_fields() == false)
			return;
		// Client test
		System.out.println("=========================");
		System.out.println("TESTAR GÊNERO");
		Genre genre = new Genre(this.name_field.getText());
		GenreDao gd = new GenreDao();
		gd.insert(genre);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Gênero criado com sucesso", AlertType.INFORMATION);
		clear();
	}
}
