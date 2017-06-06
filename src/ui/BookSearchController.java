package ui;


import daos.BookDao;
import domain.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookSearchController {

    @FXML
    private TextField search_field;

    @FXML
    private Button search_button;

    @FXML
    private TableView<Book> book_table_view;

    @FXML
    private TableColumn<Book, String> name_column;

    @FXML
    private TableColumn<Book, String> author_column;

    @FXML
    private TableColumn<Book, Integer> isbn_column;

    @FXML
    private TableColumn<Book, Integer> year_column;

    @FXML
    private TableColumn<Book, Integer> edition_column;

    @FXML
    private TableColumn<Book, Integer> page_column;

    @FXML
    public void initialize() {
    	 name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
    	 author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
    	 isbn_column.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    	 year_column.setCellValueFactory(new PropertyValueFactory<>("year"));
    	 edition_column.setCellValueFactory(new PropertyValueFactory<>("edition"));
    	 page_column.setCellValueFactory(new PropertyValueFactory<>("pages"));
    }
    
    @FXML
    void search(ActionEvent event){
    	String string_search = this.search_field.getText();
    	BookDao bd = new BookDao();
    	ObservableList<Book> books = (ObservableList<Book>) bd.find_all(string_search);
    	book_table_view.setItems((ObservableList<Book>) books);
    }
}
