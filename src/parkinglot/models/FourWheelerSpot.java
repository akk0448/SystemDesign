package parkinglot.models;

import static parkinglot.enums.VehicleType.FOUR_WHEELER;

public class FourWheelerSpot extends ParkingSpot {

    public FourWheelerSpot(int id, int price) {
        super(id, id, price, FOUR_WHEELER);
    }
}