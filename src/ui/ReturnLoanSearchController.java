package ui;

import org.sqlite.SQLiteConfig.SynchronousMode;

import daos.BookDao;
import daos.LoanDao;
import domain.Book;
import domain.Loan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import lib.AlertLib;

public class ReturnLoanSearchController {

    @FXML
    private Button search_button;

    @FXML
    private TableView<Loan> loan_table_view;

    @FXML
    private TableColumn<Loan, Integer> code_table_column;

    @FXML
    private TableColumn<Loan, Integer> day_table_column;

    @FXML
    private Button return_button;
    
    @FXML
    public void initialize(){
    	code_table_column.setCellValueFactory(new PropertyValueFactory<>("code"));
    	day_table_column.setCellValueFactory(new PropertyValueFactory<>("lend_date"));
    }
    
    @FXML
    public void search(){
    	LoanDao ld = new LoanDao();
    	ObservableList<Loan> loans = (ObservableList<Loan>) ld.find_activated_loans();
		loan_table_view.setItems((ObservableList<Loan>) loans);
		code_table_column.setCellValueFactory(new PropertyValueFactory<>("code"));
		day_table_column.setCellValueFactory(new PropertyValueFactory<>("lend_date"));
    }
    
    @FXML
    public void return_loan(ActionEvent event){
    	Loan loan = loan_table_view.getSelectionModel().getSelectedItem();
    	if (loan == null){
    		AlertLib a = new AlertLib();
    		a.create_message("Erro", "Precisa selecionar 1 empréstimo", AlertType.ERROR);
    		return;
    	}
    	loan.return_loan();
    	AlertLib al = new AlertLib();
    	al.create_message("Sucesso", "Empréstimo devolvido com sucesso", AlertType.INFORMATION);
    	search();
    }

}
