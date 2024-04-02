package com.yogith.tickets.entity;

import java.util.Date;

public class Receipt {
    private Ticket ticket;
    private Date purchaseDate;

    public Receipt(Ticket ticket) {
        this.ticket = ticket;
        this.purchaseDate = new Date(); // Assuming purchase date is current date/time
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // Method to generate receipt details
    public String generateReceipt() {
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("Receipt Details:\n");
        receiptBuilder.append("From: ").append(ticket.getFrom()).append("\n");
        receiptBuilder.append("To: ").append(ticket.getTo()).append("\n");
        receiptBuilder.append("User: ").append(ticket.getUser().getFirstName()).append(" ")
                      .append(ticket.getUser().getLastName()).append("\n");
        receiptBuilder.append("Email: ").append(ticket.getUser().getEmail()).append("\n");
        receiptBuilder.append("Price Paid: ").append(ticket.getSeatNo()).append("\n");
        receiptBuilder.append("Purchase Date: ").append(purchaseDate).append("\n");
        return receiptBuilder.toString();
    }
}