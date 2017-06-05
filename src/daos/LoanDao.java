package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Loan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoanDao {
	Connection connection;
	
	public LoanDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void return_loan(Loan loan){
		String sql = "UPDATE loan set status = 1 WHERE id = ?";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setObject(1, loan.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Loan> find_activated_loans() {
		String sql = "SELECT id, code, lend_date, return_date FROM loan WHERE status=0";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
	        ObservableList<Loan> loans = FXCollections.observableArrayList();
			while (rs.next()) {
				Loan loan = new Loan(rs.getInt("code"), rs.getDate("lend_date"), rs.getDate("return_date"));
				loan.setId(UUID.fromString(rs.getString("id")));
				loans.add(loan);
			}
			rs.close();
			ps.close();
			return loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Loan loan){
		String sql = ("INSERT INTO loan (id, code, lend_date, client_id, librarian_id, return_date, status) " +
					  "VALUES (?, (SELECT IFNULL(MAX(code), 0) + 1 FROM loan), ?, ?, ?, ?, ?)");
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			UUID id = UUID.randomUUID();
			ps.setObject(1, id);
			loan.setId(id);
			ps.setDate(2, loan.getLend_date());
			ps.setObject(3, loan.getClient_id());
			ps.setObject(4, loan.getEmployee_id());
			ps.setObject(5, loan.getReturn_date());
			ps.setObject(6, loan.getStatus());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
