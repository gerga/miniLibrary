package ui;

import java.net.URL;
import java.util.ResourceBundle;

import daos.BookDao;
import daos.GenreDao;
import domain.Book;
import domain.Genre;
import domain.Book.BookStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import lib.AlertLib;

public class BookEditorController implements Initializable {
	@Override

	public void initialize(URL location, ResourceBundle resources) {
		prefill_genre_combo();
		setNumberType(this.code_field);
		setNumberType(this.year_field);
		setNumberType(this.edition_field);
		setNumberType(this.page_field);
	}
	
	private void setNumberType(TextField field){
		// force the field to be numeric only
		field.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					field.setText(newValue.replaceAll("[^\\d]", ""));
				}
			}
		});
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

	@FXML
	private TextField code_field;

	@FXML
	private Button search_field;

	private boolean check_fields() {
		AlertLib a = new AlertLib();
		if (this.name_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Nome não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.author_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Campo autor precisa ser preenchido", AlertType.WARNING);
			return false;
		} else if (this.isbn_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Campo ISBN precisa ser preenchido", AlertType.WARNING);
			return false;
		} else if (this.year_field.getText().trim().isEmpty()) {
			a.create_message("Erro", "Campo ano não pode ser vazio", AlertType.WARNING);
			return false;
		} else if (this.genre_combo.getSelectionModel().isEmpty()) {
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
	void prefill_genre_combo() {
		GenreDao gd = new GenreDao();
		ObservableList<Genre> genres = (ObservableList<Genre>) gd.select();
		genre_combo.setItems((ObservableList<Genre>) genres);
	}

	@FXML
	private void search() {
		if (this.code_field.getText().isEmpty()) {
			AlertLib a = new AlertLib();
			a.create_message("Erro", "Digite um código antes", AlertType.WARNING);
			return;
		}

		BookDao bd = new BookDao();
		Book book = bd.find_by_code(this.code_field.getText());
		if (book != null) {
			this.code_field.setEditable(false);
			this.name_field.setText(book.getName());
			this.author_field.setText(book.getAuthor());
			this.isbn_field.setText(book.getIsbn());
			this.year_field.setText(String.valueOf(book.getYear()));
			this.edition_field.setText(String.valueOf(book.getEdition()));
			this.page_field.setText(String.valueOf(book.getPages()));
		}
	}

	private void clear() {
		this.code_field.setText("");
		this.name_field.setText("");
		this.author_field.setText("");
		this.isbn_field.setText("");
		this.year_field.setText("");
		this.edition_field.setText("");
		this.page_field.setText("");
		this.genre_combo.getSelectionModel().clearSelection();
	}

	private void update(){
		Genre genre = genre_combo.getSelectionModel().getSelectedItem();
		Book book = new Book(this.name_field.getText(), this.author_field.getText(), this.isbn_field.getText(),
				Integer.parseInt(this.year_field.getText()), Integer.parseInt(this.edition_field.getText()),
				Integer.parseInt(this.page_field.getText()), genre, BookStatus.available);
		book.setCode(Integer.valueOf(this.code_field.getText()));
		BookDao bd = new BookDao();
		bd.update(book);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Livro atualizado com sucesso", AlertType.INFORMATION);	
	}
	
	private void insert() {
		Genre genre = genre_combo.getSelectionModel().getSelectedItem();
		Book book = new Book(this.name_field.getText(), this.author_field.getText(), this.isbn_field.getText(),
				Integer.parseInt(this.year_field.getText()), Integer.parseInt(this.edition_field.getText()),
				Integer.parseInt(this.page_field.getText()), genre, BookStatus.available);
		BookDao bd = new BookDao();
		bd.insert(book);
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Livro criado com sucesso", AlertType.INFORMATION);
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
