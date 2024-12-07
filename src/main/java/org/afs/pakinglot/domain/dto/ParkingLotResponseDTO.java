package org.afs.pakinglot.domain.dto;

import java.util.List;

public class ParkingLotResponseDTO {
    private int id;
    private String name;
    private int capacity;
    private List<TicketResponseDTO> tickets;

    public ParkingLotResponseDTO(int id, String name, List<TicketResponseDTO> tickets, int capacity) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
        this.capacity = capacity;
    }

    public ParkingLotResponseDTO() {

    }

    // Getters and Setters
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketResponseDTO> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketResponseDTO> tickets) {
        this.tickets = tickets;
    }
}
