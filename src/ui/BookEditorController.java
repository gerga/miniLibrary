package ui;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import daos.BookDao;
import daos.GenreDao;
import domain.Book;
import domain.Genre;
import domain.Book.BookStatus;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lib.AlertLib;

public class BookEditorController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		prefill_genre_combo();
	} 
	
    @FXML
    private TextField name_field;

    @FXML
    private TextField author_field;

    @FXML
    private TextField isbn_field;

    @FXML
    private TextField year_field;

    @FXML
    private TextField edition_field;

    @FXML
    private TextField page_field;

    @FXML
    private ComboBox<Genre> genre_combo;

    @FXML
    private Button confirm_button;
    
    @FXML
    private Button cancel_button;
    
    private boolean check_fields() {
    	AlertLib a = new AlertLib();
		if (this.name_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Nome não pode ser vazio", AlertType.WARNING);
			return false;
		}else if (this.author_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo autor precisa ser preenchido", AlertType.WARNING);
			return false;
		}else if(this.isbn_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo ISBN precisa ser preenchido", AlertType.WARNING);
			return false;
		}else if (this.year_field.getText().trim().isEmpty()){
			a.create_message("Erro", "Campo ano não pode ser vazio", AlertType.WARNING);
			return false;
		}else if (this.genre_combo.getSelectionModel().isEmpty()){
			a.create_message("Erro", "Gênero não pode ser vazio", AlertType.WARNING);
			return false;
		}
		return true;
	}
    
    @FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
    
    @FXML
    void prefill_genre_combo(){
    	GenreDao gd = new GenreDao();
    	ObservableList<Genre> genres = (ObservableList<Genre>) gd.select();
    	genre_combo.setItems((ObservableList<Genre>) genres);
    }
    
    private void clear(){
    	this.name_field.setText("");
    	this.author_field.setText("");
    	this.isbn_field.setText("");
    	this.year_field.setText("");
    	this.edition_field.setText("");
    	this.page_field.setText("");
    	this.genre_combo.getSelectionModel().clearSelection();
    }
    
    @FXML
    void confirm(ActionEvent event) {
    	if (check_fields() == false)
    		return;
    	//	Client test
    	System.out.println("=========================");
    	System.out.println("TESTAR LIVRO");
    	Genre genre = genre_combo.getSelectionModel().getSelectedItem();
    	Book book = new Book(this.name_field.getText(), this.author_field.getText(), this.isbn_field.getText(),
    						 Integer.parseInt(this.year_field.getText()), Integer.parseInt(this.edition_field.getText()),
    						 Integer.parseInt(this.page_field.getText()),
    						 genre, BookStatus.available);
    	BookDao bd = new BookDao();
    	bd.insert(book);
    	AlertLib a = new AlertLib();
    	a.create_message("Sucesso", "Livro criado com sucesso", AlertType.INFORMATION);
    	clear();
    }

}
