package lib;

import java.time.LocalDate;

import domain.Book;
import domain.Book.BookStatus;
import domain.Client;
import domain.Genre;
import domain.Librarian;
import domain.Loan;

public class MainApp {
	public static void main(String[] args) {
		// Criando gênero
		Genre genre = new Genre("Aventura");

		// Criando cliente
		Client client = new Client("Marcia Andrade", "marcia@andrade.com", "(12) 3231-0242");
		System.out.println("Cliente: " + client.getName());

		// Criando funcionário
		Librarian librarian = new Librarian("Alex Ferreira", "alex@ferreira.com", "(11) 3141-2314", "412.123.412-22",
				"Bibliotecário");
		System.out.println("Funcionário: " + librarian.getName());

		// Emprestar livro
		// Criando livro
		Book book = new Book("Zé Aventura", "João Marques", "1234", 2016, 1, 238, genre, BookStatus.available);
		Book book2 = new Book("Mario Drama", "João Marques", "41231", 2016, 1, 238, genre, BookStatus.available);
		LocalDate lend_date = LocalDate.now();
		LocalDate return_date = lend_date.plusDays(7);
		System.out.println(lend_date + "  " + return_date);
		Loan loan = new Loan(lend_date, client, librarian, return_date);
		loan.add_book(book);
		loan.add_book(book2);
		
		// Retornar empréstimo
		loan.return_loan();
		
	}
}
