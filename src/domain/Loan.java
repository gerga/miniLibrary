package domain;

import java.sql.Date;
import java.util.UUID;

public class Loan {
	private UUID id;
	private Date lend_date;
	private UUID client_id;
	private UUID employee_id;
	private Date return_date;
	private int status;
	private Date returned_date;
	
	public Loan(Date lend_date, Client client, Librarian librarian, Date return_date){
		this.lend_date = lend_date;
		this.client_id = client.getId();
		this.employee_id = librarian.getId();
		this.return_date = return_date;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getLend_date() {
		return lend_date;
	}

	public void setLend_date(Date lend_date) {
		this.lend_date = lend_date;
	}

	public UUID getClient_id() {
		return client_id;
	}

	public void setClient_id(UUID client_id) {
		this.client_id = client_id;
	}

	public UUID getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(UUID employee_id) {
		this.employee_id = employee_id;
	}

	public Date getReturn_date() {
		return return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getReturned_date() {
		return returned_date;
	}

	public void setReturned_date(Date returned_date) {
		this.returned_date = returned_date;
	}
	
	
}
