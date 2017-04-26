package domain;

import java.util.UUID;

public class LoanItem {
	private UUID id;
	private UUID book_id;
	private UUID loan_id;
	
	public LoanItem(Book book, Loan loan){
		this.book_id = book.getId();
		this.loan_id = loan.getId();
	}
}
