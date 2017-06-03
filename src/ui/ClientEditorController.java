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
import lib.AlertLib;
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
	
	private boolean check_fields(){
		AlertLib a = new AlertLib();
		if(this.name_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo NOME não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.email_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo EMAIL não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.phone_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo TELEFONE não pode ser vazio", AlertType.WARNING);
			return false;
		}
		return true;
	}
	
	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
	@FXML
	void confirm(ActionEvent event) {
		if (check_fields() == false)
			return;
		// Client test
		System.out.println("=========================");
		System.out.println("CRIANDO CLIENTE");
		Client client = new Client(this.name_field.getText(), this.email_field.getText(), this.email_field.getText());
		ClientDao cd = new ClientDao();
		cd.insert(client);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Cliente criado com sucesso", AlertType.INFORMATION);
		clear();
	}
}
