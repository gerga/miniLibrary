package domain;

import java.util.UUID;

import domain.Book.BookStatus;

public class LoanBook {
	private UUID id;
	private UUID book_id;
	private UUID loan_id;
	
	public LoanBook(Book book, Loan loan){
		this.book_id = book.getId();
		this.loan_id = loan.getId();
		book.setStatus(BookStatus.loaned.getStatus());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getBook_id() {
		return book_id;
	}

	public void setBook_id(UUID book_id) {
		this.book_id = book_id;
	}

	public UUID getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(UUID loan_id) {
		this.loan_id = loan_id;
	}
	
	
}
