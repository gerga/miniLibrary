package domain;

import java.util.UUID;

public class Librarian extends Person{
	private UUID id;
	private Integer code;
	private String cpf;
	private UUID person_id;
	
	public Librarian(String name, String email, String phone, String cpf){
		super(name, email, phone);
		this.id = UUID.randomUUID();
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UUID getPerson_id() {
		return person_id;
	}

	public void setPerson_id(UUID person_id) {
		this.person_id = person_id;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
}
