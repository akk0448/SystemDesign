package com.example.parkinglot.models;

import com.example.parkinglot.strategy.ParkingStrategy;

import java.util.List;

public class FourWheelerManager extends ParkingSpotManager {

    public FourWheelerManager(List<ParkingSpot> spots, ParkingStrategy strategy) {
        super(spots, strategy);
    }
}