package domain;

import java.util.UUID;

public class LendItem {
	private UUID id;
	private UUID book_id;
	private UUID lend_id;
	
	public LendItem(Book book, Lending lending){
		this.book_id = book.getId();
		this.lend_id = lending.getId();
	}
}
