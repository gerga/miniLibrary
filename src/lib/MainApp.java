package lib;

import domain.Book;
import domain.Client;
import domain.Genre;
import domain.Librarian;

public class MainApp {
	public static void main(String[] args) {
//		Criando gênero
		Genre genre = new Genre("Aventura");
//		Criando livro
		Book book = new Book("Zé Aventura", "João Marques", "1234", 2016, 1, 238, genre, 0);
		System.out.println("Livro: " + book.getName());
		
//		Criando cliente
		Client client = new Client("Marcia Andrade", "marcia@andrade.com", "(12) 3231-0242");
		System.out.println("Cliente: " + client.getName());
		
//		Criando funcionário
		Librarian employee_person = new Librarian("Alex Ferreira", "alex@ferreira.com", "(11) 3141-2314",
												  "412.123.412-22", "Bibliotecário");
		System.out.println("Funcionário: " + employee_person.getName());
		
	}
}
