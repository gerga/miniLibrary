package ui;

import daos.ClientDao;
import domain.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
		this.code_field.setEditable(true);
		this.code_field.setText("");
		this.name_field.setText("");
		this.email_field.setText("");
		this.phone_field.setText("");
	}

	private boolean check_fields() {
		AlertLib a = new AlertLib();
		if (this.name_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Campo NOME não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.email_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Campo EMAIL não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.phone_field.getText().trim().isEmpty()) {
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
	private void search() {
		if (this.code_field.getText().isEmpty()) {
			AlertLib a = new AlertLib();
			a.create_message("Erro", "Digite um código antes", AlertType.WARNING);
			return;
		}
		ClientDao cd = new ClientDao();
		Client client = cd.find_by_code(this.code_field.getText());
		if (client != null){
			this.code_field.setEditable(false);
			this.name_field.setText(client.getName());
			this.email_field.setText(client.getEmail());
			this.phone_field.setText(client.getPhone());
		}
	}
	
	private void update(){
		Client client = new Client(this.name_field.getText(), this.email_field.getText(), this.email_field.getText());
		client.setCode(Integer.parseInt(this.code_field.getText()));
		ClientDao cd = new ClientDao();
		cd.update(client);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Cliente atualizado com sucesso", AlertType.INFORMATION);
	}
	
	private void insert(){
		Client client = new Client(this.name_field.getText(), this.email_field.getText(), this.email_field.getText());
		ClientDao cd = new ClientDao();
		cd.insert(client);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Cliente criado com sucesso", AlertType.INFORMATION);
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
