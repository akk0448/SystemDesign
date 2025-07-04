package com.example.parkinglot;

import com.example.parkinglot.enums.VehicleType;
import com.example.parkinglot.models.*;
import com.example.parkinglot.registry.SpotRegistry;
import com.example.parkinglot.strategy.DefaultParkingStrategy;
import com.example.parkinglot.strategy.FarthestToGateParkingStrategy;
import com.example.parkinglot.strategy.NearestToGateParkingStrategy;
import com.example.parkinglot.strategy.ParkingStrategy;

import java.util.*;

public class ParkingLotMain {

    private static final int NUM_VEHICLES = 200;
    private static final int NUM_SPOTS = 300;
    private static final int NUM_GATES = 3;
    private static final Random rand = new Random();

    public void simulateParkingLot() {
        // 1. Create parking spots
        List<ParkingSpot> spots = new ArrayList<>();
        for (int i = 1; i <= NUM_SPOTS; ++i) {
            if (i <= NUM_SPOTS / 2)
                spots.add(new TwoWheelerSpot(i, 10));
            else
                spots.add(new FourWheelerSpot(i, 20));
        }

        SpotRegistry spotRegistry = new SpotRegistry(spots);

        // 2. Create entrance and exit gates
        List<EntranceGate> entranceGates = new ArrayList<>();
        List<ExitGate> exitGates = new ArrayList<>();
        Set<Integer> usedPositions = new HashSet<>();

        for (int i = 1; i <= NUM_GATES; ++i) {
            int entryPos, exitPos;

            do {
                entryPos = rand.nextInt(NUM_SPOTS) + 1;
            } while (!usedPositions.add(entryPos));

            do {
                exitPos = rand.nextInt(NUM_SPOTS) + 1;
            } while (!usedPositions.add(exitPos));

            entranceGates.add(new EntranceGate("Entrance " + i, entryPos));
            exitGates.add(new ExitGate("Exit " + i, exitPos));
        }
        List<Gate> gates = new ArrayList<>(entranceGates);
        gates.addAll(exitGates);

        // 3. Create vehicles and simulate entry
        List<Ticket> activeTickets = new ArrayList<>();

        for (int i = 0; i < NUM_VEHICLES; i++) {
            VehicleType type = rand.nextBoolean() ? VehicleType.TWO_WHEELER : VehicleType.FOUR_WHEELER;
            Vehicle vehicle = new Vehicle("VEH-" + i, type);

            EntranceGate gate = entranceGates.get(rand.nextInt(NUM_GATES));
            Gate referenceGate = gates.get(rand.nextInt(NUM_GATES));
            ParkingStrategy strategy = getRandomStrategy(spotRegistry);

            try {
                ParkingSpot spot = gate.findParkingSpace(vehicle, spots, strategy, referenceGate);
                Ticket ticket = gate.generateTicket(vehicle, spot, spotRegistry);
                activeTickets.add(ticket);
                System.out.println("🅿️ Parked " + vehicle.getVehicleType().getType() +
                        " with vehicle number " + vehicle.getVehicleNo() +
                        " at spot " + spot.getId() +
                        " using " + strategy.getClass().getSimpleName() +
                        " via entrance " + gate.getGateName() + " at position " + gate.getPosition() +
                        " (strategy reference: " + referenceGate.getGateName() + " at position " + referenceGate.getPosition() + ")");
                System.out.println(ticket);
            } catch (Exception e) {
                System.out.println("❌ Failed to park " + vehicle.getVehicleNo() + ": " + e.getMessage());
            }
        }

        // 4. Simulate random vehicle exits
        Collections.shuffle(activeTickets);
        for (int i = 0; i < activeTickets.size() / 2; i++) {
            Ticket ticket = activeTickets.get(i);
            ExitGate gate = exitGates.get(rand.nextInt(NUM_GATES));

            try {
                gate.removeVehicle(ticket, spotRegistry);
                System.out.println("🚗 Exited vehicle " + ticket.getVehicle().getVehicleNo() + " from spot " + ticket.getParkingSpot().getId());
            } catch (Exception e) {
                System.out.println("❌ Failed to exit vehicle: " + e.getMessage());
            }
        }

        // 5. Final spot availability summary
        System.out.println("\n--- Final Spot Status ---");
        for (ParkingSpot spot : spots) {
            System.out.println("Spot " + spot.getId() + " [" + spot.getVehicleType() + "]: " + (spot.isEmpty() ? "Available" : "Occupied"));
        }
    }

    private ParkingStrategy getRandomStrategy(SpotRegistry registry) {
        int strategyType = ParkingLotMain.rand.nextInt(3);
        return switch (strategyType) {
            case 0 -> new DefaultParkingStrategy();
            case 1 -> new NearestToGateParkingStrategy(registry);
            case 2 -> new FarthestToGateParkingStrategy(registry);
            default -> throw new IllegalStateException("Unexpected strategy");
        };
    }
}