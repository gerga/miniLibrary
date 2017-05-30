package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Book;
import domain.Book.BookStatus;
import domain.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDao {
	Connection connection;
	
	public BookDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public List<Book> find_all(){
		String sql = "SELECT * FROM book";
		PreparedStatement ps;
		try{
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ObservableList<Book> books = FXCollections.observableArrayList();
			while (rs.next()){
				System.out.println(rs.getObject("genre_id"));
				Book book = new Book(rs.getString("name"), rs.getString("author"), rs.getString("isbn"),
									 rs.getInt("year"), rs.getInt("edition"), rs.getInt("pages"), rs.getString("genre_id"),
									 rs.getInt("status"));
				books.add(book);
			}
			rs.close();
			ps.close();
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Book book){
		String sql = "INSERT INTO book(id, name, author, isbn, year, edition, pages, genre_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			UUID id = UUID.randomUUID();
			ps.setObject(1, id);
			ps.setString(2, book.getName());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getIsbn());
			ps.setInt(5, book.getYear());
			ps.setInt(6, book.getEdition());
			ps.setInt(7, book.getPages());
			// TRABALHAR NISSO
			ps.setObject(8, book.getGenre_id());
			ps.setInt(9, book.getStatus());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
