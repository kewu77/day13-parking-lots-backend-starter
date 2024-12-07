package org.afs.pakinglot.domain.controller;

import org.afs.pakinglot.domain.Car;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.dto.FetchRequestDTO;
import org.afs.pakinglot.domain.dto.ParkRequestDTO;
import org.afs.pakinglot.domain.dto.ParkingLotResponseDTO;
import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.service.ParkingManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parking")
@CrossOrigin(origins = "http://localhost:3000")
public class ParkingManagerController {

    private final ParkingManagerService service;

    @Autowired
    public ParkingManagerController(ParkingManagerService service) {
        this.service = service;
    }

    @GetMapping("/getAllData")
    public List<ParkingLotResponseDTO> getAllData() {
        return service.getAllData();
    }

    @PostMapping("/park")
    public Ticket park(@RequestBody ParkRequestDTO request) {
        return service.park(request.getStrategy(), request.getPlateNumber());
    }

    @PostMapping("/fetch")
    public Car fetch(@RequestBody FetchRequestDTO request) {
        return service.fetch(request.getPlateNumber());
    }
}
