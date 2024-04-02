package com.yogith.tickets.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yogith.tickets.dto.TrainAppRepository;
import com.yogith.tickets.entity.Receipt;
import com.yogith.tickets.entity.Ticket;
import com.yogith.tickets.entity.TicketPurchaseRequest;
import com.yogith.tickets.entity.User;

@Service
public class TicketPurchaseService {

	@Autowired
	TrainAppRepository trainAppRepository;

	public Receipt submitTicketPurchase(TicketPurchaseRequest request) {
		Random random = new Random();

		User user = new User(request.getFirstName(), request.getLastName(), request.getEmail());
		Ticket ticket = new Ticket(random.nextLong(), request.getFrom(), request.getTo(), user, request.getSeatNo());
		trainAppRepository.submitTicketPurchase(ticket);
		return new Receipt(ticket);
	}
}