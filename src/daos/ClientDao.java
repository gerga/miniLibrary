package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import domain.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDao {
	Connection connection;
	
	public ClientDao(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public List<Client> find_by_name(String string_search){
		String sql = "select person.id, person.name, person.email, person.phone, client.code from person inner join client on client.person_id = person.id WHERE person.name LIKE '%" + string_search + "%'";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
	        ObservableList<Client> clients = FXCollections.observableArrayList();
			while (rs.next()) {
				Client client = new Client(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
				client.setCode(rs.getInt("code"));
				client.setId(UUID.fromString(rs.getString("id")));
				clients.add(client);
			}
			rs.close();
			ps.close();
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Client find_by_code(String code){
		String sql = "select person.id, person.name, person.email, person.phone from person inner join client on client.person_id = person.id WHERE client.code = " + code;
		PreparedStatement ps;
		try {
			Client client = null;
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				client = new Client(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
				client.setId(UUID.fromString(rs.getString("id")));
			}
			rs.close();
			ps.close();
			return client;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Client> find_all(){
		String sql = "select person.id, person.name, person.email, person.phone from person inner join client on client.person_id = person.id";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
	        ObservableList<Client> clients = FXCollections.observableArrayList();
			while (rs.next()) {
				Client client = new Client(rs.getString("name"), rs.getString("email"), rs.getString("phone"));
				client.setId(UUID.fromString(rs.getString("id")));
				clients.add(client);
			}
			rs.close();
			ps.close();
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void insert(Client client){
		String person_sql = "INSERT INTO person(id, name, email, phone) VALUES (?, ?, ?, ?)";
		String client_sql = "INSERT INTO client(id, code, person_id) VALUES (?, (SELECT IFNULL(MAX(code), 0) + 1 FROM client), ?)";
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
	
	public void update(Client client){
		String person_sql = "UPDATE person set name = ?, email=?, phone=? WHERE id = (select person_id from client where code = ?)";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(person_sql);
			// Use 'setObject' to JDBC recognize UUID type
			ps.setString(1, client.getName());
			ps.setString(2, client.getEmail());
			ps.setString(3, client.getPhone());
			ps.setObject(4, client.getCode());
			ps.execute();
			
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
