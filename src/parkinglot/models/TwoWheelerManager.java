package parkinglot.models;

import parkinglot.strategy.ParkingStrategy;

import java.util.List;

public class TwoWheelerManager extends ParkingSpotManager {

    public TwoWheelerManager(List<ParkingSpot> spots, ParkingStrategy strategy) {
        super(spots, strategy);
    }
}