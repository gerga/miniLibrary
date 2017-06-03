package ui;

import daos.ClientDao;
import daos.LibrarianDao;
import domain.Client;
import domain.Librarian;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lib.AlertLib;

public class LibrarianEditorController {
	
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

	private void clear(){
		this.name_field.setText("");
		this.email_field.setText("");
		this.phone_field.setText("");
		this.cpf_field.setText("");
	}
	
	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
	private boolean check_fields(){
		AlertLib a = new AlertLib();
		if (this.name_field.getText().isEmpty()){
			a.create_message("Erro", "Campo nome não pode ser vazio", AlertType.WARNING);
			return false;
		} else if(this.email_field.getText().isEmpty()){
			a.create_message("Erro", "Campo EMAIL não pode ser vazio", AlertType.WARNING);
			return false;
		} else if(this.phone_field.getText().isEmpty()){
			a.create_message("Erro", "Campo TELEFONE não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.cpf_field.getText().isEmpty()){
			a.create_message("Erro", "Campo CPF não pode ser vazio", AlertType.WARNING);
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
		System.out.println("CRIANDO BIBLIOTECÁRIO");
		Librarian librarian = new Librarian(this.name_field.getText(), this.email_field.getText(), this.phone_field.getText(),
											this.cpf_field.getText());
		LibrarianDao ld = new LibrarianDao();
		ld.insert(librarian);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Bibliotecário criado com sucesso", AlertType.INFORMATION);
		clear();
	}

}
