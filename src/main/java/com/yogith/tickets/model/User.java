package com.yogith.tickets.model;

public class User {
    private static int userCount = 0;
    private final String firstName;
    private final String lastName;
    private final String email;
    private int userId;

    public User(String firstName, String lastName, String email) {
        this.userId = generateUserId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private synchronized int generateUserId() {
        return ++userCount;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}


