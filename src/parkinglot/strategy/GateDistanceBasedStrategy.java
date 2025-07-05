package parkinglot.strategy;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Vehicle;
import parkinglot.registry.SpotRegistry;

import java.util.List;
import java.util.NavigableSet;

public abstract class GateDistanceBasedStrategy extends ParkingStrategy {

    protected final SpotRegistry spotRegistry;

    protected GateDistanceBasedStrategy(SpotRegistry spotRegistry) {
        this.spotRegistry = spotRegistry;
    }

    protected int distanceFromGate(ParkingSpot spot, Gate gate) {
        return Math.abs(spot.getPosition() - gate.getPosition());
    }

    @Override
    public ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> spots, Gate referenceGate) {
        NavigableSet<ParkingSpot> set = spotRegistry.getParkingSpots(vehicle.getVehicleType());
        if (set == null || set.isEmpty()) {
            throw new IllegalStateException("No available spots for: " + vehicle.getVehicleType());
        }

        synchronized (set) {
            return selectSpot(set, referenceGate);
        }
    }

    protected abstract ParkingSpot selectSpot(NavigableSet<ParkingSpot> set, Gate referenceGate);
}