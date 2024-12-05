package org.afs.pakinglot.domain.dto;

public class FetchRequestDTO {
    private String plateNumber;

    public FetchRequestDTO() {
    }

    public FetchRequestDTO(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    // Getters and Setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
