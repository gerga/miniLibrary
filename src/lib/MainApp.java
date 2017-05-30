package lib;

import java.io.IOException;
import java.time.LocalDate;

import domain.Book;
import domain.Book.BookStatus;
import domain.Client;
import domain.Genre;
import domain.Librarian;
import domain.Loan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application{
	
	public void start(Stage primaryStage) throws IOException {
		Pane root = FXMLLoader.load(getClass().
				getResource("/ui/MainApp.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Aplicativo");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
//	public static void main(String[] args) {
//		// Criando gênero
//		System.out.println("Criando gênero");
//		Genre genre = new Genre("Aventura");
//		System.out.println("------------------");
//
//		// Criando cliente
//		System.out.println("Criando cliente");
//		Client client = new Client("Marcia Andrade", "marcia@andrade.com", "(12) 3231-0242");
//		System.out.println("Cliente: " + client.getName());
//		System.out.println("------------------");
//
//		// Criando funcionário
//		System.out.println("Criando funcionário");
//		Librarian librarian = new Librarian("Alex Ferreira", "alex@ferreira.com", "(11) 3141-2314", "412.123.412-22",
//				"Bibliotecário");
//		System.out.println("Funcionário: " + librarian.getName());
//		System.out.println("------------------");
//
//		// Reservar livro
//		System.out.println("Reservar livro");
//		Book reservation_book = new Book("Maria Aventura", "João Marques", "1234", 2016, 1, 238, genre, BookStatus.available);
//		reservation_book.to_reserve(client, librarian);
////		reservation_book.return_reservation();
//		System.out.println("------------------");
//		
//		// Emprestar livro
//		// Criando livro
//		Book book = new Book("Zé Aventura", "João Marques", "1234", 2016, 1, 238, genre, BookStatus.available);
//		Book book2 = new Book("Mario Drama", "João Marques", "41231", 2016, 1, 238, genre, BookStatus.available);
//		LocalDate lend_date = LocalDate.now();
//		LocalDate return_date = lend_date.plusDays(7);
//		Loan loan = new Loan(lend_date, client, librarian, return_date);
//		loan.add_book(book);
//		loan.add_book(book2);
//		loan.add_book(reservation_book);
//		reservation_book.return_reservation();
//		loan.add_book(reservation_book);
//		System.out.println("----------------Novo empréstimo-------------");
//		Loan loan2 = new Loan(lend_date, client, librarian, return_date);
//		loan.add_book(book);
//		
//		// Retornar empréstimo
//		loan.return_loan();
//		
//		
//	}
}
