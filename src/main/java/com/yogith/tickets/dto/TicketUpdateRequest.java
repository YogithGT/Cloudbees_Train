package com.yogith.tickets.dto;

public class TicketUpdateRequest {
    private String userEmail;
    private boolean switchSeatSection;
    private String from;
    private String to;

    public TicketUpdateRequest() {
        // Default constructor
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean getSwitchSeatSection() {
        return switchSeatSection;
    }

    public void setSwitchSeatSection(boolean flag) {
        this.switchSeatSection = flag;
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
}

