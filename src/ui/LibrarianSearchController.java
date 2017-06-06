package ui;

import daos.LibrarianDao;
import domain.Librarian;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LibrarianSearchController {

    @FXML
    private TextField search_field;

    @FXML
    private Button search_button;

    @FXML
    private TableView<Librarian> librarian_table_view;

    @FXML
    private TableColumn<Librarian, Integer> code_column;

    @FXML
    private TableColumn<Librarian, String> name_column;

    @FXML
    private TableColumn<Librarian, String> email_column;

    @FXML
    private TableColumn<Librarian, String> phone_column;

    @FXML
    public void initialize(){
    	code_column.setCellValueFactory(new PropertyValueFactory<>("code"));
    	name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
    	email_column.setCellValueFactory(new PropertyValueFactory<>("email"));
    	phone_column.setCellValueFactory(new PropertyValueFactory<>("phone"));
    }
    
    @FXML
    void search(ActionEvent event) {
    	String string_search = this.search_field.getText();
    	LibrarianDao ld = new LibrarianDao();
    	ObservableList<Librarian> librarians = (ObservableList<Librarian>) ld.find_by_name(string_search);
    	librarian_table_view.setItems((ObservableList<Librarian>) librarians);
    }

}
