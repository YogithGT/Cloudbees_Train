package com.yogith.tickets.dto;
import com.yogith.tickets.model.User;

public class UserSeatResponse {
    private final User user;
    private final String seatSection;

    public UserSeatResponse(User user, String seatSection) {
        this.user = user;
        this.seatSection = seatSection;
    }

    public User getUser() {
        return user;
    }

    public String getSeatSection() {
        return seatSection;
    }
}
