package com.yogith.tickets.entity;

public class Ticket {
	private Long userId;
	private String from;
	private String to;
	private User user;
	private String seatNo;

	public Ticket(Long userId, String from, String to, User user, String seatNo) {
		super();
		this.userId = userId;
		this.from = from;
		this.to = to;
		this.user = user;
		this.seatNo = seatNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

}