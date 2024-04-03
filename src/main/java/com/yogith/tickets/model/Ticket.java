package com.yogith.tickets.model;

public class Ticket {
    private static int ticketCount = 0;
    private final User user;
    private final int ticketId;
    private String from;
    private String to;
    private double price;
    private String seatSection = "NA";

    public Ticket(String from, String to, User user, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.ticketId = generateTicketId();
        this.from = from;
        this.to = to;
        this.user = user;
        this.price = price;
    }

    private synchronized int generateTicketId() {
        return ++ticketCount;
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

    public double getPrice() {
        return price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getSeatSection() {
        return seatSection;
    }

    public void setSeatSection(String seatSection) {
        this.seatSection = seatSection;
    }
}


