package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Book;
import domain.Loan;
import domain.LoanBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanBookDao {
	Connection connection;
	
	public LoanBookDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public List<Book> find_loan_books(Loan loan) {
		String sql = "SELECT book.id, book.status FROM book INNER JOIN loan_book ON loan_book.book_id = book.id INNER JOIN loan ON loan_book.loan_id = ?;";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ps.setObject(1, loan.getId());
			ResultSet rs = ps.executeQuery();
	        ObservableList<Book> books = FXCollections.observableArrayList();
			while (rs.next()) {
				Book book = new Book(UUID.fromString(rs.getString("id")), rs.getInt("status"));
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
	
	public void insert(LoanBook loan_book){
		String sql = ("INSERT INTO loan_book(id, book_id, loan_id) VALUES (?, ?, ?)");
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			UUID id = UUID.randomUUID();
			ps.setObject(1, id);
			ps.setObject(2, loan_book.getBook_id());
			ps.setObject(3, loan_book.getLoan_id());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
