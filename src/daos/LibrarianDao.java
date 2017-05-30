package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import domain.Client;
import domain.Librarian;

public class LibrarianDao {
Connection connection;
	
	public LibrarianDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Librarian librarian){
		String person_sql = "INSERT INTO person(id, name, email, phone) VALUES (?, ?, ?, ?)";
		String librarian_sql = "INSERT INTO librarian(id, cpf, person_id) VALUES (?, ?, ?)";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(person_sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setObject(1, librarian.getPerson_id());
			ps.setString(2, librarian.getName());
			ps.setString(3, librarian.getEmail());
			ps.setString(4, librarian.getPhone());
			ps.execute();
			
			ps = this.connection.prepareStatement(librarian_sql);
			ps.setObject(1, librarian.getId());
			ps.setString(2, librarian.getCpf());
			ps.setObject(3, librarian.getPerson_id());
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
