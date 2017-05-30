package domain;

import java.time.LocalDate;
import java.util.UUID;

import domain.Book.BookStatus;

public class Booking {
	private UUID id;
	private UUID client_id;
	private UUID book_id;
	private UUID responsible_id;
	private LocalDate start_date;
	private LocalDate end_date;
	private LocalDate return_date;

	public Booking(Client client, Book book, Librarian librarian) {
		if (book.getStatus() == BookStatus.reserved.getStatus())
			System.out.println("Livro já emprestado");
		else if (book.getStatus() == BookStatus.reserved.getStatus())
			System.out.println("Livro já reservado - Data dd/mm/yyyy");
		else if (book.getStatus() == BookStatus.available.getStatus()) {
			LocalDate start_date = LocalDate.now();
			LocalDate end_date = start_date.plusDays(3);
			
			this.client_id = client.getId();
			this.book_id = book.getId();
			this.responsible_id = librarian.getId();
			this.start_date = start_date;
			this.end_date = end_date;
		}
	}
}
