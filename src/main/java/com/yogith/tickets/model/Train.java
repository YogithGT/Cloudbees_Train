package com.yogith.tickets.model;

import java.util.Random;
import java.util.UUID;

public class Train {

    private final String name;
    private final String ID;
    private final int maxCapacity;
    private final Random random = new Random();
    private int LoadACount;
    private int LoadBCount;

    public Train(String name, int maxCapacity) {
        this.name = name;
        this.ID = generateTicketId();
        this.maxCapacity = maxCapacity;
        this.LoadACount = 0;
        this.LoadBCount = 0;
    }

    public static String generateTicketId() {
        return UUID.randomUUID().toString();
    }

    public boolean isFull() {
        return LoadACount + LoadBCount >= maxCapacity;
    }

    /**
     * Allocates a seat in the train based on availability and random selection.
     *
     * @return The allocated seat ("Load A", "Load B", or "NA" if both are full).
     */
    public String allocateSeat() {
        boolean isLoadASelected = random.nextBoolean();

        // Check if Section A has available seats
        if (isLoadASelected && LoadACount <= maxCapacity / 2) {
            LoadACount++;
            return "Load A";
        }

        // Check if Section B has available seats
        if (LoadBCount <= maxCapacity / 2) {
            LoadBCount++;
            return "Load B";
        }

        // Both sections are full, return "NA"
        return "NA";
    }

    public boolean isSectionFull(String section) {
        String loadA = "Load A";
        String loadB = "Load B";

        if (loadA.equalsIgnoreCase(section)) {
            return LoadACount >= maxCapacity / 2;
        } else if (loadB.equalsIgnoreCase(section)) {
            return LoadBCount >= maxCapacity / 2;
        } else {
            // Unknown section, throw an exception or handle accordingly
            throw new IllegalArgumentException("Unknown section: " + section);
        }
    }

    public void incrementSectionCount(String section) {
        if ("Load A".equalsIgnoreCase(section)) {
            LoadACount++;
        } else if ("Load B".equalsIgnoreCase(section)) {
            LoadBCount++;
        }
    }

    public void decrementSectionCount(String section) {
        if ("Load A".equalsIgnoreCase(section)) {
            LoadACount--;
        } else if ("Load B".equalsIgnoreCase(section)) {
            LoadBCount--;
        }
    }

}

