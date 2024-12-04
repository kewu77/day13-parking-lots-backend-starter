package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.strategies.AvailableRateStrategy;
import org.afs.pakinglot.domain.strategies.MaxAvailableStrategy;
import org.afs.pakinglot.domain.strategies.SequentiallyStrategy;

import java.util.List;

public class ParkingManager {
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



}
