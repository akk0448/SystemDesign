package com.example.parkinglot.models;

import com.example.parkinglot.strategy.ParkingStrategy;

import java.util.List;

public class TwoWheelerManager extends ParkingSpotManager {

    public TwoWheelerManager(List<ParkingSpot> spots, ParkingStrategy strategy) {
        super(spots, strategy);
    }
}