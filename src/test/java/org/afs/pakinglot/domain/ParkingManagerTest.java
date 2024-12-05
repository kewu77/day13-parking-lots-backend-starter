package org.afs.pakinglot.domain;

import org.afs.pakinglot.domain.exception.NoAvailablePositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ParkingManagerTest {
    //case 1: should return all parking lots when get all parking lots given parking manager
    //case 2: should return ticket when park given parking manager and plate number
    //case 3: should return car when fetch given parking manager and plate number
    //case 4: should throw exception when fetch given parking manager and wrong plate number
    //case 5: should throw exception when park given parking manager and parking lot is full

    private ParkingManager parkingManager;

    @BeforeEach
    void setUp() {
        parkingManager = new ParkingManager();
    }

    @Test
    void should_return_all_parking_lots_when_get_all_parking_lots_given_parking_manager() {
        // Given

        // When
        List<ParkingLot> parkingLots = parkingManager.getAllParkingLots();

        // Then
        assertEquals(3, parkingLots.size());
        assertEquals("The Plaza Park", parkingLots.get(0).getName());
        assertEquals("City Mall Garage", parkingLots.get(1).getName());
        assertEquals("Office Tower Parking", parkingLots.get(2).getName());
    }

    @Test
    void should_return_ticket_when_park_given_parking_manager_and_plate_number() {
        // Given
        String strategy = "SequentiallyStrategy";
        String plateNumber = "AB-1234";

        // When
        Ticket ticket = parkingManager.park(strategy, plateNumber);

        // Then
        assertNotNull(ticket);
        assertEquals(plateNumber, ticket.plateNumber());
    }

    @Test
    void should_return_car_when_fetch_given_parking_manager_and_plate_number() {
        // Given
        String strategy = "SequentiallyStrategy";
        String plateNumber = "AB-1234";
        parkingManager.park(strategy, plateNumber);

        // When
        Car car = parkingManager.fetch(plateNumber);

        // Then
        assertNotNull(car);
        assertEquals(plateNumber, car.plateNumber());
    }

    @Test
    void should_throw_exception_when_fetch_given_parking_manager_and_wrong_plate_number() {
        // Given
        String plateNumber = "AB-1234";

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> parkingManager.fetch(plateNumber));
    }

    @Test
    void should_throw_exception_when_park_given_parking_manager_and_parking_lot_is_full() {
        // Given
        String strategy = "SequentiallyStrategy";
        for (int i = 0; i < 30; i++) {
            parkingManager.park(strategy, "AB-123" + new Random().nextInt(10));
        }

        // When & Then
        assertThrows(NoAvailablePositionException.class, () -> parkingManager.park(strategy, "AB-1239"));
    }
}
