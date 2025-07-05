package parkinglot.models;

import parkinglot.enums.VehicleType;
import parkinglot.registry.SpotRegistry;

import java.time.LocalDateTime;

public abstract class ParkingSpot {

    private final int id;
    private final int position;
    private final int price;
    private final VehicleType vehicleType;
    private boolean isEmpty;
    private Vehicle vehicle;
    private LocalDateTime parkedAt;

    public ParkingSpot(int id, int position, int price, VehicleType vehicleType) {
        this.id = id;
        this.position = position;
        this.price = price;
        this.vehicleType = vehicleType;
        this.isEmpty = true;
    }

    public void parkVehicle(Vehicle vehicle, SpotRegistry spotRegistry) {
        if (!isEmpty) {
            throw new IllegalStateException("Parking spot " + id + " is already occupied.");
        }
        this.vehicle = vehicle;
        this.isEmpty = false;
        this.parkedAt = LocalDateTime.now();
        spotRegistry.markAsOccupied(this);
    }

    public void removeVehicle(SpotRegistry spotRegistry) {
        this.vehicle = null;
        this.isEmpty = true;
        this.parkedAt = null;
        spotRegistry.markAsVacant(this);
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getPrice() {
        return price;
    }

    public LocalDateTime getParkedAt() {
        return parkedAt;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean supportsVehicleType(VehicleType type) {
        return this.vehicleType == type;
    }

    @Override
    public String toString() {
        return "Spot#" + id + " [" + (isEmpty ? "Empty" : "Occupied by " + vehicle.getVehicleNo()) + "]";
    }
}