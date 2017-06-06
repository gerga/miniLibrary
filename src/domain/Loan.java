package domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import daos.BookDao;
import daos.LoanBookDao;
import daos.LoanDao;
import domain.Book.BookStatus;
import javafx.collections.ObservableList;

public class Loan {
	private UUID id;
	private int code;
	private Date lend_date;
	private UUID client_id;
	private UUID employee_id;
	private Date return_date;
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
	
	public Loan(Integer code, Date lend_date, Date return_date){
		this.code = code;
		this.lend_date = lend_date;
		this.return_date = return_date;
	}
	
	public Loan(Date lend_date, Client client, Librarian librarian, Date return_date){
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
			LoanBook loan_book = new LoanBook(book, this);
			LoanBookDao lbd = new LoanBookDao();
			lbd.insert(loan_book);
			// Change book status
			book.setStatus(BookStatus.loaned.getStatus());
			BookDao bd = new BookDao();
			bd.update_status(book);
		}
	}
	
	public void return_loan(){
		System.out.println("Buscar livros com o respectivo id desse empréstimo");
		LoanBookDao lbd = new LoanBookDao();
		ObservableList<Book> loan_books = (ObservableList<Book>) lbd.find_loan_books(this);
		for (Book book : loan_books){
			book.setStatus(BookStatus.available.getStatus());
			BookDao bd = new BookDao();
			bd.update_status(book);
		}
		LoanDao ld = new LoanDao();
		ld.return_loan(this);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getLend_date() {
		return lend_date;
	}

	public void setLend_date(Date lend_date) {
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

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
