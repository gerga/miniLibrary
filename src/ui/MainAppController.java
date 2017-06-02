package ui;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainAppController {

	@FXML
	void close_app(ActionEvent event) {
		Platform.exit();
	}
	
	@FXML
	void search_activated_loans(ActionEvent event){
		System.out.println("Abrindo Pesquisa de Empréstimos Ativos");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReturnLoanSearch.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Pesquisa Empréstimos");
		stage.setScene(new Scene(editor));
		stage.show();
	}
	
	@FXML
	void create_loan(ActionEvent event){
		System.out.println("Abrindo Wizard de Empréstimo");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoanWizard.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Novo Empréstimo");
		stage.setScene(new Scene(editor));
		stage.show();
	}

	@FXML
	void open_book_search(ActionEvent event){
		System.out.println("Abrindo Pesquisa de Livros");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookSearch.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Pesquisa de Livros");
		stage.setScene(new Scene(editor));
		stage.show();
	}
	
	@FXML
	void create_genre(ActionEvent event) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GenreEditor.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Editor de Gênero");
		stage.setScene(new Scene(editor));
		stage.show();
	}

	@FXML
	void create_book(ActionEvent event) {
		System.out.println("Abrindo editor de Livro");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BookEditor.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Editor de Livro");
		stage.setScene(new Scene(editor));
		stage.show();
	}

	@FXML
	void create_client(ActionEvent event) {
		System.out.println("Abrindo editor de Cliente");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientEditor.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Editor de Cliente");
		stage.setScene(new Scene(editor));
		stage.show();
	}

	@FXML
	void create_librarian(ActionEvent event) {
		System.out.println("Abrindo editor de bibliotecário");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LibrarianEditor.fxml"));
		Parent editor = null;
		try {
			editor = (Parent) fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage = new Stage();
		stage.setTitle("Editor de Bibliotecário");
		stage.setScene(new Scene(editor));
		stage.show();
	}
}
