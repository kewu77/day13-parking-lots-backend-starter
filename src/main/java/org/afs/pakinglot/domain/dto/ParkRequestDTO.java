package org.afs.pakinglot.domain.dto;

public class ParkRequestDTO {
    private String strategy;
    private String plateNumber;

    public ParkRequestDTO() {
    }

    public ParkRequestDTO(String strategy, String plateNumber) {
        this.strategy = strategy;
        this.plateNumber = plateNumber;
    }

    // Getters and Setters
    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
