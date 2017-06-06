package ui;

import daos.ClientDao;
import domain.Client;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientSearchController {

    @FXML
    private TextField search_field;

    @FXML
    private Button search_button;

    @FXML
    private TableView<Client> client_table_view;

    @FXML
    private TableColumn<Client, Integer> code_column;
    
    @FXML
    private TableColumn<Client, String> name_column;

    @FXML
    private TableColumn<Client, String> email_column;

    @FXML
    private TableColumn<Client, String> phone_column;

    @FXML
    public void initialize(){
    	code_column.setCellValueFactory(new PropertyValueFactory<>("code"));
    	name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
    	email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
    	phone_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    
    @FXML
    void search(ActionEvent event){
    	String string_search = this.search_field.getText();
    	ClientDao cd = new ClientDao();
    	ObservableList<Client> clients = (ObservableList<Client>) cd.find_by_name(string_search);
    	client_table_view.setItems((ObservableList<Client>) clients);
    }
    
}
