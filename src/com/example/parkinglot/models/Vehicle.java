package com.example.parkinglot.models;

import com.example.parkinglot.enums.VehicleType;

import java.util.Objects;

public class Vehicle {

    private final String vehicleNo;
    private final VehicleType vehicleType;

    public Vehicle(String vehicleNo, VehicleType vehicleType) {
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle vehicle)) {
            return false;
        }
        return Objects.equals(vehicleNo, vehicle.vehicleNo) &&
                vehicleType == vehicle.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleNo, vehicleType);
    }

    @Override
    public String toString() {
        return vehicleType + " - " + vehicleNo;
    }
}