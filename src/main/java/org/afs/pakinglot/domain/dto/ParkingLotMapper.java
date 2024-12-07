package org.afs.pakinglot.domain.dto;

import org.afs.pakinglot.domain.ParkingLot;
import org.afs.pakinglot.domain.Ticket;
import org.afs.pakinglot.domain.dto.ParkingLotResponseDTO;
import org.afs.pakinglot.domain.dto.TicketResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotMapper {
    public static ParkingLotResponseDTO toResponseDTO(ParkingLot parkingLot) {
        List<TicketResponseDTO> tickets = parkingLot.getTickets().stream()
                .map(ticket -> new TicketResponseDTO(ticket.plateNumber(), ticket.position(), ticket.parkingLot()))
                .collect(Collectors.toList());
        return new ParkingLotResponseDTO(parkingLot.getId(), parkingLot.getName(), tickets,parkingLot.getCapacity());
    }
}
