package com.yogith.tickets.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yogith.tickets.entity.Receipt;
import com.yogith.tickets.entity.Ticket;
import com.yogith.tickets.entity.User;

@Repository
public class TrainAppRepository {

	private static List<Ticket> tickets;
	private static Map<User, String> usersAndSeats = new HashMap<>();
	private static Map<Long, User> users = new HashMap<>();

	static {

		User user1 = new User("Alice", "Smith", "alice@example.com");
		User user2 = new User("Bob", "Johnson", "bob@example.com");
		usersAndSeats.put(user1, "Seat A1");
		usersAndSeats.put(user2, "Seat B2");

		users.put(1L, new User("Alice", "Smith", "alice@example.com"));
		users.put(2L, new User("Bob", "Johnson", "bob@example.com"));
	}

	public void submitTicketPurchase(Ticket ticket) {
		usersAndSeats.put(ticket.getUser(), ticket.getSeatNo());
		tickets.add(ticket);
	}

	public Receipt getReceiptByUserId(Long userId) {
		Ticket localTicket = tickets.stream()
			    .filter(ticket -> ticket.getUserId() == userId)
			    .findFirst()
			    .orElse(null);

		return new Receipt(localTicket);
	}

	public Map<User, String> getUsersAndSeats() {
		return usersAndSeats;
	}

	public boolean removeUser(Long userId) {
		Ticket localTicket = tickets.stream()
			    .filter(ticket -> ticket.getUserId() == userId)
			    .findFirst()
			    .orElse(null);

		return tickets.remove(localTicket);
	}

}

