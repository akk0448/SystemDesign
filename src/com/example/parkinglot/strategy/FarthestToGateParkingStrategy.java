package com.example.parkinglot.strategy;

import com.example.parkinglot.models.Gate;
import com.example.parkinglot.models.ParkingSpot;
import com.example.parkinglot.registry.SpotRegistry;

import java.util.Comparator;
import java.util.NavigableSet;

public class FarthestToGateParkingStrategy extends GateDistanceBasedStrategy {

    public FarthestToGateParkingStrategy(SpotRegistry spotRegistry) {
        super(spotRegistry);
    }

    @Override
    protected ParkingSpot selectSpot(NavigableSet<ParkingSpot> set, Gate referenceGate) {
        return set.stream()
                .max(Comparator.comparingInt(spot -> distanceFromGate(spot, referenceGate)))
                .orElseThrow(() -> new IllegalStateException("No suitable parking spot found far from gate: " + referenceGate.getGateName()));
    }
}