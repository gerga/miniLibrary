package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Librarian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LibrarianDao {
Connection connection;
	
	public LibrarianDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Librarian find_by_code(String code){
		String sql = "select person.id, person.name, person.email, person.phone, librarian.cpf from person inner join librarian on librarian.person_id = person.id WHERE librarian.code = " + code;
		PreparedStatement ps;
		try {
			Librarian librarian = null;
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				librarian = new Librarian(rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("cpf"));
				librarian.setId(UUID.fromString(rs.getString("id")));
			}
			rs.close();
			ps.close();
			return librarian;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Librarian> find_all(){
		String sql = "select person.id, person.name, person.email, person.phone, librarian.cpf from person inner join librarian on librarian.person_id = person.id";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
	        ObservableList<Librarian> librarians = FXCollections.observableArrayList();
			while (rs.next()) {
				Librarian librarian= new Librarian(rs.getString("name"), rs.getString("email"), rs.getString("phone"), rs.getString("cpf"));
				librarian.setId(UUID.fromString(rs.getString("id")));
				librarians.add(librarian);
			}
			rs.close();
			ps.close();
			return librarians;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Librarian librarian){
		String person_sql = "INSERT INTO person(id, name, email, phone) VALUES (?, ?, ?, ?)";
		String librarian_sql = "INSERT INTO librarian(id, code, cpf, person_id) VALUES (?, (SELECT IFNULL(MAX(code), 0) + 1 FROM librarian), ?, ?)";
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
	
	public void update(Librarian librarian){
		String person_sql = "UPDATE person set name = ?, email=?, phone=? WHERE id = (select person_id from librarian where code = ?)";
		String librarian_sql = "UPDATE librarian set cpf=? WHERE code = ?";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(person_sql);
			ps.setString(1, librarian.getName());
			ps.setString(2, librarian.getEmail());
			ps.setString(3, librarian.getPhone());
			ps.setInt(4, librarian.getCode());
			ps.execute();
			
			ps = this.connection.prepareStatement(librarian_sql);
			ps.setString(1, librarian.getCpf());
			ps.setInt(2, librarian.getCode());
			ps.execute();
			
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
