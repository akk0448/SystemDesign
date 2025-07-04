package parkinglot.strategy;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Vehicle;

import java.util.List;

public class DefaultParkingStrategy extends ParkingStrategy {

    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> parkingSpots, Gate referenceGate) {
        return parkingSpots.stream()
                .filter(ParkingSpot::isEmpty)
                .filter(spot -> spot.getVehicleType().equals(vehicle.getVehicleType()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No available spot for vehicle type: " + vehicle.getVehicleType()));
    }
}