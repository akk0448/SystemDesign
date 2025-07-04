package com.example.parkinglot.strategy;

import com.example.parkinglot.models.Gate;
import com.example.parkinglot.models.ParkingSpot;
import com.example.parkinglot.models.Vehicle;

import java.util.List;

public abstract class ParkingStrategy {

    public abstract ParkingSpot findParkingSpot(Vehicle vehicle, List<ParkingSpot> parkingSpots, Gate referenceGate);
}