package domain;

import java.util.UUID;

public class Client extends Person{
	private UUID id;
	private UUID person_id;
	
	public Client(String name, String email, String phone){
		super(name, email, phone);
		this.id = UUID.randomUUID();
		this.person_id = super.getId();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPerson_id() {
		return this.person_id;
	}

	public void setPerson_id(UUID person_id) {
		this.person_id = person_id;
	}
}
