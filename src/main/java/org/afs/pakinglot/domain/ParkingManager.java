package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;
import java.util.regex.Pattern;

public class ParkingManager {
    public static final Pattern PLATE_NUMBER_PATTERN = Pattern.compile("[A-Z]{2}-\\d{4}");


    private final List<ParkingLot> parkingLots = List.of(
            new ParkingLot(1, "The Plaza Park", 9),
            new ParkingLot(2, "City Mall Garage", 12),
            new ParkingLot(3, "Office Tower Parking", 9)
    );

    private final List<ParkingBoy> parkingBoys = List.of(
            new ParkingBoy(parkingLots, new SequentiallyStrategy()),
            new ParkingBoy(parkingLots, new MaxAvailableStrategy()),
            new ParkingBoy(parkingLots, new AvailableRateStrategy())
    );

    public List<ParkingLot> getAllParkingLots() {
        return parkingLots;
    }


    public Ticket park(String strategy, String plateNumber) {
        if (!PLATE_NUMBER_PATTERN.matcher(plateNumber).matches()) {
            throw new IllegalArgumentException("Invalid plate number format: " + plateNumber);
        }
        ParkingBoy selectedParkingBoy;
        switch (strategy) {
            case "SequentiallyStrategy":
                selectedParkingBoy = parkingBoys.get(0);
                break;
            case "MaxAvailableStrategy":
                selectedParkingBoy = parkingBoys.get(1);
                break;
            case "AvailableRateStrategy":
                selectedParkingBoy = parkingBoys.get(2);
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
        Car car = new Car(plateNumber);
        return selectedParkingBoy.park(car);
    }

    public Car fetch(String plateNumber) {
        Ticket ticket = parkingLots.stream()
                .flatMap(parkingLot -> parkingLot.getTickets().stream())
                .filter(t -> t.plateNumber().equals(plateNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found for plate number: " + plateNumber));
        return parkingBoys.get(0).fetch(ticket);
    }


}
