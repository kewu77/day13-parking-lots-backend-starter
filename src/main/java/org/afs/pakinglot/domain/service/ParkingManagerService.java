package org.afs.pakinglot.domain.service;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.ParkingBoy;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.dto.ParkingLotMapper;
import org.afs.pakinglot.domain.dto.ParkingLotResponseDTO;
import org.afs.pakinglot.domain.repository.ParkingManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ParkingManagerService {
    public static final Pattern PLATE_NUMBER_PATTERN = Pattern.compile("[A-Z]{2}-\\d{4}");

    private final ParkingManagerRepository repository;

    public ParkingManagerService(ParkingManagerRepository repository) {
        this.repository = repository;
    }

    public List<ParkingLot> getAllParkingLots() {
        return repository.getAllParkingLots();
    }

    public List<ParkingLotResponseDTO> getAllData() {
        List<ParkingLot> parkingLots = repository.getAllParkingLots();
        return parkingLots.stream()
                .map(ParkingLotMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Ticket park(String strategy, String plateNumber) {
        if (!PLATE_NUMBER_PATTERN.matcher(plateNumber).matches()) {
            throw new IllegalArgumentException("Invalid plate number format: " + plateNumber);
        }
        ParkingBoy selectedParkingBoy;
        switch (strategy) {
            case "SequentiallyStrategy":
                selectedParkingBoy = repository.getParkingBoys().get(0);
                break;
            case "MaxAvailableStrategy":
                selectedParkingBoy = repository.getParkingBoys().get(1);
                break;
            case "AvailableRateStrategy":
                selectedParkingBoy = repository.getParkingBoys().get(2);
                break;
            default:
                throw new IllegalArgumentException("Unknown strategy: " + strategy);
        }
        Car car = new Car(plateNumber);
        return selectedParkingBoy.park(car);
    }

    public Car fetch(String plateNumber) {
        Ticket ticket = repository.getAllParkingLots().stream()
                .flatMap(parkingLot -> parkingLot.getTickets().stream())
                .filter(everyTicket -> everyTicket.plateNumber().equals(plateNumber))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found for plate number: " + plateNumber));
        return repository.getParkingBoys().get(0).fetch(ticket);
    }
}
