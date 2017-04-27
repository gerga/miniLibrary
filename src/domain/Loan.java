package domain;

import java.time.LocalDate;
import java.util.UUID;

import domain.Book.BookStatus;

public class Loan {
	private UUID id;
	private LocalDate lend_date;
	private UUID client_id;
	private UUID employee_id;
	private LocalDate return_date;
	private int status;
	private LocalDate returned_date;
	
	public enum LoanStatus{
		/* Create enum to manipulate status with strings, but save it as int
		 */
		activated(0), disabled(1);
		private final int status;
		private LoanStatus(int status_value) {
			status = status_value;
		}
		
		public int getStatus(){
			return status;
		}
	}
	
	public Loan(LocalDate lend_date, Client client, Librarian librarian, LocalDate return_date){
		this.lend_date = lend_date;
		this.client_id = client.getId();
		this.employee_id = librarian.getId();
		this.return_date = return_date;
		this.status = LoanStatus.activated.getStatus();
	}
	
	public void add_book(Book book){
		if (book.getStatus() == BookStatus.reserved.getStatus())
			System.out.println("Impossivel emprestar livro. Se encontra reservado");
		else if (book.getStatus() == BookStatus.loaned.getStatus())
			System.out.println("Esse livro já se encontra emprestado");
		else if (book.getStatus() == BookStatus.available.getStatus()){
			System.out.println("Livro pode ser emprestado");
			// Create LoanItem
			new LoanBook(book, this);
		}
	}
	
	public void return_loan(){
		System.out.println("Buscar livros com o respectivo id desse empréstimo");
//		Book.BookStatus = BookStatus.available.getStatus();
		this.status = LoanStatus.disabled.getStatus();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getLend_date() {
		return lend_date;
	}

	public void setLend_date(LocalDate lend_date) {
		this.lend_date = lend_date;
	}

	public UUID getClient_id() {
		return client_id;
	}

	public void setClient_id(UUID client_id) {
		this.client_id = client_id;
	}

	public UUID getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(UUID employee_id) {
		this.employee_id = employee_id;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(LocalDate returned_date) {
		this.returned_date = returned_date;
	}
	
	
}
