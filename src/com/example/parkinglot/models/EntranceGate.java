package com.example.parkinglot.models;

import com.example.parkinglot.factory.ParkingSpotManagerFactory;
import com.example.parkinglot.registry.SpotRegistry;
import com.example.parkinglot.strategy.ParkingStrategy;

import java.time.LocalDateTime;
import java.util.List;

public class EntranceGate extends Gate {

    public EntranceGate(String gateName, int position) {
        super(gateName, position);
    }

    public ParkingSpot findParkingSpace(Vehicle vehicle, List<ParkingSpot> spots, ParkingStrategy strategy, Gate referenceGate) {
        ParkingSpotManager manager = ParkingSpotManagerFactory.getParkingSpotManager(vehicle.getVehicleType(), spots, strategy);
        return manager.findParkingSpace(vehicle, referenceGate);
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot parkingSpot, SpotRegistry spotRegistry) {
        if (vehicle == null || parkingSpot == null || !parkingSpot.isEmpty()) {
            throw new IllegalArgumentException("Cannot generate ticket: invalid vehicle or spot not available.");
        }

        parkingSpot.parkVehicle(vehicle, spotRegistry);
        return new Ticket(LocalDateTime.now(), parkingSpot, vehicle);
    }
}