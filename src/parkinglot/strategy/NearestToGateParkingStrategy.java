package parkinglot.strategy;

import parkinglot.models.Gate;
import parkinglot.models.ParkingSpot;
import parkinglot.registry.SpotRegistry;

import java.util.Comparator;
import java.util.NavigableSet;

public class NearestToGateParkingStrategy extends GateDistanceBasedStrategy {

    public NearestToGateParkingStrategy(SpotRegistry spotRegistry) {
        super(spotRegistry);
    }

    @Override
    protected ParkingSpot selectSpot(NavigableSet<ParkingSpot> set, Gate referenceGate) {
        return set.stream()
                .min(Comparator.comparingInt(spot -> distanceFromGate(spot, referenceGate)))
                .orElseThrow(() -> new IllegalStateException("No suitable parking spot found near gate: " + referenceGate.getGateName()));
    }
}