package domain;
import java.util.UUID;

public class Genre {
	private UUID id;
	private int code;
	private String name;
	
	@Override
	public String toString() {
		return this.getName();
	}
	
	public Genre(String name){
		this.name = name;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
