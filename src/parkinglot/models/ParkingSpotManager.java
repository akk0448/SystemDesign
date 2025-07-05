package parkinglot.models;

import parkinglot.strategy.ParkingStrategy;

import java.util.List;

public abstract class ParkingSpotManager {

    protected final List<ParkingSpot> parkingSpots;
    protected final ParkingStrategy parkingStrategy;

    public ParkingSpotManager(List<ParkingSpot> parkingSpots, ParkingStrategy parkingStrategy) {
        this.parkingSpots = parkingSpots;
        this.parkingStrategy = parkingStrategy;
    }

    protected ParkingSpot findParkingSpace(Vehicle vehicle, Gate referenceGate) {
        return parkingStrategy.findParkingSpot(vehicle, parkingSpots, referenceGate);
    }
}
