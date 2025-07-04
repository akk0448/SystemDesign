package com.example.parkinglot.models;

import static com.example.parkinglot.utils.ApplicationUtils.generateTicketId;

public class Ticket {

    private final String id;
    private final long entryTime;
    private final ParkingSpot parkingSpot;
    private final Vehicle vehicle;

    public Ticket(long entryTime, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.id = generateTicketId();
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", entryTime=" + entryTime +
                ", spot=" + parkingSpot.getId() +
                '}';
    }
}