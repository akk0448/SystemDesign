package com.example.parkinglot.factory;

import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.models.*;
import com.example.parkinglot.strategy.ParkingStrategy;

import java.util.List;

public class ParkingSpotManagerFactory {

    public static ParkingSpotManager getParkingSpotManager(VehicleType vehicleType, List<ParkingSpot> spots, ParkingStrategy strategy) {
        return switch (vehicleType) {
            case TWO_WHEELER -> new TwoWheelerManager(spots, strategy);
            case FOUR_WHEELER -> new FourWheelerManager(spots, strategy);
            default -> throw new IllegalArgumentException("Unsupported vehicle type: " + vehicleType);
        };
    }
}