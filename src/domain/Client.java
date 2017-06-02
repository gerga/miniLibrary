package domain;

import java.util.UUID;

public class Client extends Person{
	private UUID id;
	private int code;
	private UUID person_id;
	
	public Client(String name, String email, String phone){
		super(name, email, phone);
		this.id = UUID.randomUUID();
		this.person_id = super.getId();
	}

	@Override
	public String toString(){
		return this.getName();
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
