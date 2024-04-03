package com.yogith.tickets.controller;

// models and data transfer object imports

import com.yogith.tickets.dto.TicketUpdateRequest;
import com.yogith.tickets.model.Ticket;
import com.yogith.tickets.model.Train;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/train")
public class TicketController {

    private final Map<String, Ticket> ticketStore = new HashMap<>();
    // All the ticketRelated details are persisted in ticketStore; email unique for every user, I decided to map email to ticketObj
    private final Train train = new Train("SampleTrain", 2);

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "Health Check OK";
    }

    @PostMapping
    public ResponseEntity<String> purchaseTicket(@RequestBody Ticket ticket) {
        String userEmail = ticket.getUser().getEmail();
        if (ticketStore.containsKey(userEmail)) {
            return ResponseEntity.badRequest().body("User with email " + userEmail + " already has a ticket.");
        }
        if (train.isFull()) {
            return ResponseEntity.badRequest().body("Train capacity is full. Cannot allocate more seats.");
        }
        String seatSection = train.allocateSeat();
        ticket.setSeatSection(seatSection);
        ticketStore.put(ticket.getUser().getEmail(), ticket);
        return ResponseEntity.ok("Ticket purchased successfully! Your Ticket ID:  " + ticket.getTicketId());
    }

    @GetMapping("/fetch/{userEmail}")
    public ResponseEntity<?> getTicketDetails(@PathVariable String userEmail) {
        Ticket ticket = ticketStore.get(userEmail);
        if (ticket != null)
            return ResponseEntity.ok(ticket);
        return null;
    }

   @GetMapping("/passengers")
   public Map<String, String> getUsersAndSeats() {
       Map<String, String> userSeats = new HashMap<>();
       for (Map.Entry<String, Ticket> entry : ticketStore.entrySet()) {
           userSeats.put(entry.getKey(), entry.getValue().getSeatSection());
       }
       return userSeats;
   }

    @DeleteMapping("/cancel/{userEmail}")
    public ResponseEntity<String> removeUserFromTrain(@PathVariable String userEmail) {
        if (ticketStore.containsKey(userEmail)) {
            Ticket removedTicket = ticketStore.remove(userEmail);
            // Decrement the section count based on the removed ticket's seat section
            train.decrementSectionCount(removedTicket.getSeatSection());
            return ResponseEntity.ok("User removed from the train");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found for cancellation with email: " + userEmail);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<String> modifyUserSeat(@RequestBody TicketUpdateRequest updateRequest) {
        String userEmail = updateRequest.getUserEmail();

        if (!ticketStore.containsKey(userEmail)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found for seat modification with email: " + userEmail);
        }

        Ticket existingTicket = ticketStore.get(userEmail);

        // Update seat section if requested
        if (updateRequest.getSwitchSeatSection()) {
            // Check train capacity for the new section
            String requestedSection = existingTicket.getSeatSection().equals("Load A") ? "Load B" : "Load A";

            // Check if the requested section is full
            if (train.isSectionFull(requestedSection)) {
                return ResponseEntity.badRequest().body(requestedSection + " is full. Cannot switch seats.");
            }
            // Update the seat section
            train.incrementSectionCount(requestedSection);
            train.decrementSectionCount(existingTicket.getSeatSection());
            existingTicket.setSeatSection(requestedSection);
        }
        if (updateRequest.getFrom() != null) {
            existingTicket.setFrom(updateRequest.getFrom());
        }
        if (updateRequest.getTo() != null) {
            existingTicket.setTo(updateRequest.getTo());
        }

        return ResponseEntity.ok("User's seat modified successfully!");
    }

}

