package ui;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import daos.BookDao;
import daos.ClientDao;
import daos.LibrarianDao;
import daos.LoanDao;
import domain.Book;
import domain.Client;
import domain.Librarian;
import domain.Loan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import lib.AlertLib;

public class LoanWizardController {

	@FXML
	private TextField search_field;

	@FXML
	private TableView<Book> book_view;

	@FXML
	private TableColumn<Book, String> name_column;

	@FXML
	private TableColumn<Book, String> author_column;

	@FXML
	private Button add_book_button;

	@FXML
	private TableView<Book> book_item_view;

	@FXML
	private TableColumn<Book, String> name_item_column;

	@FXML
	private TableColumn<Book, String> author_item_column;

	@FXML
	private Button remove_book_button;

	@FXML
	private ComboBox<Librarian> librarian_combo;

	@FXML
	private ComboBox<Client> client_combo;

	@FXML
	private Label return_date_label;

	@FXML
	private Button confirm_button;

	@FXML
	private Button cancel_button;

	@FXML
	public void initialize() {
		prefill_client_combo();
		prefill_librarian_combo();

		name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
		author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
		search_by_available();

		Date lend_date = new Date(Calendar.getInstance().getTimeInMillis());
		long ltime = lend_date.getTime() + 7 * 24 * 60 * 60 * 1000;
		Date return_date = new Date(ltime);

		DateFormat df = new SimpleDateFormat(" MM/dd/yyyy");
		String reportDate = df.format(return_date);
		this.return_date_label.setText(reportDate);
	}

	private void search_by_available() {
		BookDao bd = new BookDao();
		ObservableList<Book> books = (ObservableList<Book>) bd.find_available();
		book_view.setItems((ObservableList<Book>) books);
		name_item_column.setCellValueFactory(new PropertyValueFactory<>("name"));
		author_item_column.setCellValueFactory(new PropertyValueFactory<>("author"));
	}

	@FXML
	private void search_by_name() {
		if (this.search_field.getText().isEmpty())
			search_by_available();
		else {
			BookDao bd = new BookDao();
			ObservableList<Book> books = (ObservableList<Book>) bd.find_available_by_name(this.search_field.getText());
			book_view.setItems((ObservableList<Book>) books);
			name_item_column.setCellValueFactory(new PropertyValueFactory<>("name"));
			author_item_column.setCellValueFactory(new PropertyValueFactory<>("author"));
		}
	}

	@FXML
	void prefill_client_combo() {
		ClientDao cd = new ClientDao();
		ObservableList<Client> clients = (ObservableList<Client>) cd.find_all();
		client_combo.setItems((ObservableList<Client>) clients);
	}

	@FXML
	void prefill_librarian_combo() {
		LibrarianDao ld = new LibrarianDao();
		ObservableList<Librarian> librarians = (ObservableList<Librarian>) ld.find_all();
		librarian_combo.setItems((ObservableList<Librarian>) librarians);
	}

	private void create_book_message() {
		AlertLib a = new AlertLib();
		a.create_message("Erro", "Precisa selecionar 1 livro antes", AlertType.ERROR);
	}

	@FXML
	void add_book(ActionEvent event) {
		Book book = book_view.getSelectionModel().getSelectedItem();
		if (book == null) {
			create_book_message();
			return;
		}
		book_item_view.getItems().add(book);
		book_view.getItems().remove(book);
	}

	@FXML
	void remove_book(ActionEvent event) {
		Book book = book_item_view.getSelectionModel().getSelectedItem();
		if (book == null) {
			create_book_message();
			return;
		}
		book_view.getItems().add(book);
		book_item_view.getItems().remove(book);
	}

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	private boolean check_fields() {
		AlertLib a = new AlertLib();
		if (this.book_item_view.getItems().isEmpty()) {
			a.create_message("Erro", "É necessário adicionar 1 livro ao empréstimo", AlertType.WARNING);
			return false;
		} else if (this.client_combo.getSelectionModel().isEmpty()) {
			a.create_message("Erro", "Necessário selecionar 1 cliente", AlertType.WARNING);
			return false;
		} else if (this.librarian_combo.getSelectionModel().isEmpty()) {
			a.create_message("Erro", "Necessário selecionar 1 bibliotecário", AlertType.WARNING);
			return false;
		}
		return true;
	}

	@FXML
	void confirm(ActionEvent event) {
		if (check_fields() == false)
			return;

		// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date lend_date = new Date(Calendar.getInstance().getTimeInMillis());
		long ltime = lend_date.getTime() + 7 * 24 * 60 * 60 * 1000;
		Date return_date = new Date(ltime);

		Client client = client_combo.getSelectionModel().getSelectedItem();
		Librarian librarian = librarian_combo.getSelectionModel().getSelectedItem();
		Loan loan = new Loan(lend_date, client, librarian, return_date);
		LoanDao ld = new LoanDao();
		ld.insert(loan);
		for (Book book : book_item_view.getItems()) {
			loan.add_book(book);
		}
		AlertLib a = new AlertLib();
		a.create_message("Sucesso", "Empréstimo criado com sucesso", AlertType.INFORMATION);
		cancel(event);
	}

}
