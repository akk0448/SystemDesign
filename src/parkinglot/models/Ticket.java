package parkinglot.models;

import java.time.LocalDateTime;

import static parkinglot.utils.ApplicationUtils.generateTicketId;

public class Ticket {

    private final String id;
    private final LocalDateTime entryTime;
    private final ParkingSpot parkingSpot;
    private final Vehicle vehicle;

    public Ticket(LocalDateTime entryTime, ParkingSpot parkingSpot, Vehicle vehicle) {
        this.id = generateTicketId();
        this.entryTime = entryTime;
        this.parkingSpot = parkingSpot;
        this.vehicle = vehicle;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEntryTime() {
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