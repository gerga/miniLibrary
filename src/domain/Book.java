package domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Book {
	private UUID id;
	private String name;
	private String author;
	private String isbn;
	private int year;
	private int edition;
	private int pages;
	private UUID genre_id;
	private int status;
	
	public enum BookStatus{
		/* Create enum to manipulate status with strings, but save it as int
		 */
		available(0), reserved(1), loaned(2);
		private final int status;
		private BookStatus(int status_value) {
			status = status_value;
		}
		
		public int getStatus(){
			return status;
		}
	}
	
	public Book(String name, String author, String isbn, int year, int edition, int pages, Genre genre, BookStatus status){
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.year = year;
		this.edition = edition;
		this.pages = pages;
		this.genre_id = genre.getId();
		this.status = status.getStatus();
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public UUID getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(UUID genre_id) {
		this.genre_id = genre_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
