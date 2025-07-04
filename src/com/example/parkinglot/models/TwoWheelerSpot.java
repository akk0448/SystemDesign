package com.example.parkinglot.models;

import static com.example.parkinglot.enums.VehicleType.TWO_WHEELER;

public class TwoWheelerSpot extends ParkingSpot {

    public TwoWheelerSpot(int id, int price) {
        super(id, id, price, TWO_WHEELER);
    }
}