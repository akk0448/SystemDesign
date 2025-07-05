package parkinglot.registry;

import parkinglot.enums.VehicleType;
import parkinglot.models.ParkingSpot;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SpotRegistry {

    private final Map<VehicleType, NavigableSet<ParkingSpot>> emptySpotsMap = new ConcurrentHashMap<>();

    public SpotRegistry(List<ParkingSpot> allSpots) {
        for (ParkingSpot spot : allSpots) {
            emptySpotsMap
                    .computeIfAbsent(spot.getVehicleType(), k -> Collections.synchronizedNavigableSet(
                            new TreeSet<>(Comparator.comparingInt(ParkingSpot::getPosition))
                    ))
                    .add(spot);
        }
    }

    public void markAsOccupied(ParkingSpot spot) {
        NavigableSet<ParkingSpot> set = emptySpotsMap.get(spot.getVehicleType());
        if (set != null) synchronized (set) {
            set.remove(spot);
        }
    }

    public void markAsVacant(ParkingSpot spot) {
        NavigableSet<ParkingSpot> set = emptySpotsMap.get(spot.getVehicleType());
        if (set != null) synchronized (set) {
            set.add(spot);
        }
    }

    public NavigableSet<ParkingSpot> getParkingSpots(VehicleType vehicleType) {
        return emptySpotsMap.get(vehicleType);
    }
}