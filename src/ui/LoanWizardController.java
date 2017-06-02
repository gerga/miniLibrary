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
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
	public void initialize() {
		prefill_client_combo();
		prefill_librarian_combo();

		// TODO Auto-generated method stub
		name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
		author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
		BookDao bd = new BookDao();
		ObservableList<Book> books = (ObservableList<Book>) bd.find_available();
		book_view.setItems((ObservableList<Book>) books);
		name_item_column.setCellValueFactory(new PropertyValueFactory<>("name"));
		author_item_column.setCellValueFactory(new PropertyValueFactory<>("author"));
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

	@FXML
	void add_book(ActionEvent event) {
		Book book = book_view.getSelectionModel().getSelectedItem();
		book_item_view.getItems().add(book);
		book_view.getItems().remove(book);
	}

	@FXML
	void remove_book(ActionEvent event) {
		Book book = book_item_view.getSelectionModel().getSelectedItem();
		book_view.getItems().add(book);
		book_item_view.getItems().remove(book);
	}

	private void clear() {
		this.book_item_view.setItems(null);
		this.client_combo.getSelectionModel().clearSelection();
		this.librarian_combo.getSelectionModel().clearSelection();
	}

	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	@FXML
	void confirm(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sucesso");
		alert.setHeaderText(null);
		alert.setContentText("Empréstimo criado com sucesso!");

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
		alert.showAndWait();
		cancel(event);
	}

}
