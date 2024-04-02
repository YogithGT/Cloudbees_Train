package com.yogith.tickets.entity;

public class TicketPurchaseRequest {
	private String from;
	private String to;
	private String firstName;
	private String lastName;
	private String email;
	private String seatNo;



	public TicketPurchaseRequest(String from, String to, String firstName, String lastName, String email,
			String seatNo) {
		this.from = from;
		this.to = to;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.seatNo = seatNo;
	}

	// Getters and Setters
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
}