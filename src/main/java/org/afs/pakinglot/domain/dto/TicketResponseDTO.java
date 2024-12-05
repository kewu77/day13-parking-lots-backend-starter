package org.afs.pakinglot.domain.dto;

public class TicketResponseDTO {
    private String plateNumber;
    private int position;
    private int parkingLot;

    public TicketResponseDTO(String plateNumber, int position, int parkingLot) {
        this.plateNumber = plateNumber;
        this.position = position;
        this.parkingLot = parkingLot;
    }

    // Getters and Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(int parkingLot) {
        this.parkingLot = parkingLot;
    }
}
