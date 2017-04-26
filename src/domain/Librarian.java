package domain;

import java.util.UUID;

public class Librarian extends Person{
	private UUID id;
	private String cpf;
	private String role;
	private UUID person_id;
	
	public Librarian(String name, String email, String phone, String cpf, String role){
		super(name, email, phone);
		this.cpf = cpf;
		this.role = role;
		this.person_id = super.getId();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UUID getPerson_id() {
		return person_id;
	}

	public void setPerson_id(UUID person_id) {
		this.person_id = person_id;
	}
	
	
}
