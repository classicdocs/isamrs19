package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class VehicleDeleteDTO {

    @NotNull
    private String vehicleId;

    @NotNull
    private String rentACarId;

    public VehicleDeleteDTO() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRentACarId() {
        return rentACarId;
    }

    public void setRentACarId(String rentACarId) {
        this.rentACarId = rentACarId;
    }
}
