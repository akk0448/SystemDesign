package parkinglot.strategy;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.models.Vehicle;

import java.util.List;

public abstract class ParkingStrategy {

    public abstract ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> parkingSpots, Gate referenceGate);
}