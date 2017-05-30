package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import domain.Book;
import domain.Client;
import domain.Genre;

public class ClientDao {
	Connection connection;
	
	public ClientDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void insert(Client client){
		String person_sql = "INSERT INTO person(id, name, email, phone) VALUES (?, ?, ?, ?)";
		String client_sql = "INSERT INTO client(id, person_id) VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(person_sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setObject(1, client.getPerson_id());
			ps.setString(2, client.getName());
			ps.setString(3, client.getEmail());
			ps.setString(4, client.getPhone());
			ps.execute();
			
			ps = this.connection.prepareStatement(client_sql);
			ps.setObject(1, client.getId());
			ps.setObject(2, client.getPerson_id());
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
