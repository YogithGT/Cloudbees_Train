package com.yogith.tickets;

import com.yogith.tickets.controller.TicketController;
import com.yogith.tickets.dto.TicketUpdateRequest;
import com.yogith.tickets.model.Ticket;
import com.yogith.tickets.model.Train;
import com.yogith.tickets.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class TestController {

    private TicketController ticketController;

    @BeforeEach
    void setUp() {
        ticketController = new TicketController();
    }

    @Test
    void testHealthCheck() {
        assertEquals("Health Check OK", ticketController.healthCheck());
    }

    @Test
    void testPurchaseTicket() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0);

        ResponseEntity<String> response = ticketController.purchaseTicket(ticket);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().contains("Ticket purchased successfully!"));
    }

    @Test
    void testDuplicatePurchaseTicket() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket1 = new Ticket("London", "France", user, 20.0);
        Ticket ticket2 = new Ticket("London", "France", user, 20.0);

        ticketController.purchaseTicket(ticket1);
        ResponseEntity<String> response = ticketController.purchaseTicket(ticket2);

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("already has a ticket"));
    }

    @Test
    void testGetTicketDetails() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0);

        ticketController.purchaseTicket(ticket);
        ResponseEntity<?> response = ticketController.getTicketDetails(user.getEmail());

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testRemoveUserFromTrain() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0);

        ticketController.purchaseTicket(ticket);
        ResponseEntity<String> response = ticketController.removeUserFromTrain(user.getEmail());

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("User removed from the train"));
    }

    @Test
    void testModifyUserSeat() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0);

        ticketController.purchaseTicket(ticket);
        TicketUpdateRequest updateRequest = new TicketUpdateRequest();
        updateRequest.setUserEmail(user.getEmail());
        updateRequest.setSwitchSeatSection(true);

        ResponseEntity<String> response = ticketController.modifyUserSeat(updateRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("User's seat modified successfully"));
    }

    @Test
    void testGetUsersAndSeats() {
        User user1 = new User("John", "Doe", "john.doe@example.com");
        User user2 = new User("Jane", "Doe", "jane.doe@example.com");

        Ticket ticket1 = new Ticket("London", "France", user1, 20.0);
        Ticket ticket2 = new Ticket("London", "France", user2, 20.0);

        ticketController.purchaseTicket(ticket1);
        ticketController.purchaseTicket(ticket2);

        Map<String, String> expected = new HashMap<>();
        expected.put(user1.getEmail(), ticket1.getSeatSection());
        expected.put(user2.getEmail(), ticket2.getSeatSection());

        assertEquals(expected, ticketController.getUsersAndSeats());
    }
}
