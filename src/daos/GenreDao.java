package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import domain.Genre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GenreDao {
	Connection connection;

	public GenreDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Genre> select() {
		String sql = "SELECT * FROM genre";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
	        ObservableList<Genre> genres = FXCollections.observableArrayList();
			while (rs.next()) {
				Genre genre = new Genre(rs.getString("name"));
				genre.setId(UUID.fromString(rs.getString("id")));
				genres.add(genre);
			}
			rs.close();
			ps.close();
			return genres;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insert(Genre genre) {
		String sql = "INSERT INTO genre(id, name) VALUES (?, ?)";
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(sql);
			// Use 'setObject' to JDBC recognize UUID type
			UUID id = UUID.randomUUID();
			ps.setObject(1, id);
			ps.setString(2, genre.getName());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
