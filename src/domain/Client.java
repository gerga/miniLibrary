package domain;

import java.util.UUID;

public class Client {
	private UUID id;
	private UUID person_id;
	
	public Client(Person person){
		this.person_id = person.getId();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getPerson_id() {
		return person_id;
	}

	public void setPerson_id(UUID person_id) {
		this.person_id = person_id;
	}
}
