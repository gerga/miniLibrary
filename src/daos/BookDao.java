package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookDao {
	Connection connection;

	public BookDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void update_status(Book book) {
		String sql = "UPDATE book set status = ? WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setObject(1, book.getStatus());
			ps.setObject(2, book.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Book> find_available_by_name(String name){
		String sql = "SELECT * FROM book where status = 0 and name LIKE '%" + name + "%'";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ObservableList<Book> books = FXCollections.observableArrayList();
			while (rs.next()) {
				Book book = new Book(rs.getString("name"), rs.getString("author"), rs.getString("isbn"),
						rs.getInt("year"), rs.getInt("edition"), rs.getInt("pages"), rs.getString("genre_id"),
						rs.getInt("status"));
				book.setId(UUID.fromString(rs.getString("id")));
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
	
	public List<Book> find_available() {
		String sql = "SELECT * FROM book where status = 0";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ObservableList<Book> books = FXCollections.observableArrayList();
			while (rs.next()) {
				Book book = new Book(rs.getString("name"), rs.getString("author"), rs.getString("isbn"),
						rs.getInt("year"), rs.getInt("edition"), rs.getInt("pages"), rs.getString("genre_id"),
						rs.getInt("status"));
				book.setId(UUID.fromString(rs.getString("id")));
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

	public Book find_by_code(String code) {
		String sql = "SELECT * FROM book where code = " + code;
		PreparedStatement ps;
		try {
			Book book = null;
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				book = new Book(rs.getString("name"), rs.getString("author"), rs.getString("isbn"), rs.getInt("year"),
						rs.getInt("edition"), rs.getInt("pages"), rs.getString("genre_id"), rs.getInt("status"));
				book.setId(UUID.fromString(rs.getString("id")));
			}
			rs.close();
			ps.close();
			return book;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Book> find_all(String string_search) {
		String sql = "SELECT * FROM book";
		if (!string_search.isEmpty())
			sql = "SELECT * FROM book WHERE name LIKE '%" + string_search + "%'";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ObservableList<Book> books = FXCollections.observableArrayList();
			while (rs.next()) {
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

	public void update(Book book) {
		String sql = "UPDATE book set name=?, author=?, isbn=?, year=?, edition=?, pages=?, genre_id=? WHERE code = ?";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getIsbn());
			ps.setInt(4, book.getYear());
			ps.setInt(5, book.getEdition());
			ps.setInt(6, book.getPages());
			ps.setObject(7, book.getGenre_id());
			ps.setInt(8, book.getCode());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insert(Book book) {
		String sql = ("INSERT INTO book(id, code, name, author, isbn, year, edition, pages, genre_id, status) "
				+ "VALUES (?, (SELECT IFNULL(MAX(code), 0) + 1 FROM book), ?, ?, ?, ?, ?, ?, ?, ?)");
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
