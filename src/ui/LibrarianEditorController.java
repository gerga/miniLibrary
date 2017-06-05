package ui;

import daos.LibrarianDao;
import domain.Librarian;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lib.AlertLib;

public class LibrarianEditorController {

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

	@FXML
	private Button cancel_button;

	@FXML
	private TextField name_field;

	@FXML
	private TextField email_field;

	@FXML
	private TextField phone_field;

	@FXML
	private TextField cpf_field;

	@FXML
	private TextField code_field;

	@FXML
	private Button search_button;

	private void clear() {
		this.code_field.setEditable(true);
		this.code_field.setText("");
		this.name_field.setText("");
		this.email_field.setText("");
		this.phone_field.setText("");
		this.cpf_field.setText("");
	}

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	private boolean check_fields() {
		AlertLib a = new AlertLib();
		if (this.name_field.getText().isEmpty()) {
			a.create_message("Erro", "Campo nome não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.email_field.getText().isEmpty()) {
			a.create_message("Erro", "Campo EMAIL não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.phone_field.getText().isEmpty()) {
			a.create_message("Erro", "Campo TELEFONE não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.cpf_field.getText().isEmpty()) {
			a.create_message("Erro", "Campo CPF não pode ser vazio", AlertType.WARNING);
			return false;
		}
		return true;
	}

	@FXML
	private void search() {
		if (this.code_field.getText().isEmpty()) {
			AlertLib a = new AlertLib();
			a.create_message("Erro", "Digite um código antes", AlertType.WARNING);
			return;
		}
		LibrarianDao lbd = new LibrarianDao();
		Librarian librarian = lbd.find_by_code(this.code_field.getText());
		if (librarian != null){
			this.code_field.setEditable(false);
			this.name_field.setText(librarian.getName());
			this.email_field.setText(librarian.getEmail());
			this.phone_field.setText(librarian.getPhone());
			this.cpf_field.setText(librarian.getCpf());
		} else{
			AlertLib a = new AlertLib();
			a.create_message("Aviso", "Bibliotecário não encontrado", AlertType.WARNING);
		}
	}

	private void update(){
		Librarian librarian = new Librarian(this.name_field.getText(), this.email_field.getText(),
											this.phone_field.getText(), this.cpf_field.getText());
		librarian.setCode(Integer.parseInt(this.code_field.getText()));
		LibrarianDao lbd = new LibrarianDao();
		lbd.update(librarian);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Bibliotecário atualizado com sucesso", AlertType.INFORMATION);
	}
	
	private void insert(){
		Librarian librarian = new Librarian(this.name_field.getText(), this.email_field.getText(),
											this.phone_field.getText(), this.cpf_field.getText());
		LibrarianDao ld = new LibrarianDao();
		ld.insert(librarian);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Bibliotecário criado com sucesso", AlertType.INFORMATION);
	}
	
	@FXML
	void confirm(ActionEvent event) {
		if (check_fields() == false)
			return;
		if (!this.code_field.getText().isEmpty())
			update();
		else
			insert();
		clear();
	}

}
