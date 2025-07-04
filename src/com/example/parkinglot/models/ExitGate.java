package com.example.parkinglot.models;

import com.example.parkinglot.registry.SpotRegistry;

public class ExitGate extends Gate {

    public ExitGate(String gateName, int position) {
        super(gateName, position);
    }

    public void removeVehicle(Ticket ticket, SpotRegistry spotRegistry) {
        ParkingSpot spot = ticket.getParkingSpot();
        if (spot == null || spot.isEmpty()) {
            throw new IllegalStateException("Spot is already empty or invalid.");
        }
        spot.removeVehicle(spotRegistry);
    }
}
