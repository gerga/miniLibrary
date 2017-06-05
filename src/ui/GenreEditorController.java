package ui;

import daos.GenreDao;
import domain.Genre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lib.AlertLib;

public class GenreEditorController {

	@FXML
	private TextField name_field;

	@FXML
	private Button cancel_button;

	@FXML
	private Button confirm_button;

	@FXML
	private TextField code_field;

	@FXML
	private Button search_button;

	@FXML
	public void initialize() {
		// force the field to be numeric only
		this.code_field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					code_field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
	}

	private void clear() {
		this.code_field.setText("");
		this.name_field.setText("");
	}

	@FXML
	private void search() {
		if (this.code_field.getText().isEmpty()){
			AlertLib a = new AlertLib();
			a.create_message("Erro", "Digite um código antes", AlertType.WARNING);
			return;
		}
		GenreDao gd = new GenreDao();
		Genre genre = gd.find_by_code(this.code_field.getText());
		if (genre != null){
			this.code_field.setEditable(false);
			this.name_field.setText(genre.getName());
		}
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

	private void insert(){
		Genre genre = new Genre(this.name_field.getText());
		GenreDao gd = new GenreDao();
		gd.insert(genre);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Gênero criado com sucesso", AlertType.INFORMATION);	
	}
	
	private void update(){
		Genre genre = new Genre(this.name_field.getText());
		genre.setCode(Integer.parseInt(this.code_field.getText()));
		GenreDao gd = new GenreDao();
		gd.update(genre);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Gênero atualizado com sucesso", AlertType.INFORMATION);	
	}
	
	@FXML
	void confirm(ActionEvent event) {
		if (check_fields() == false)
			return;
		if (this.code_field.getText() != "")
			update();
		else
			insert();
		clear();
	}
}
