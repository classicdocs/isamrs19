package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class VehicleReservationDTO {

    @NotNull
    private String pickupDate;

    @NotNull
    private String pickupTime;

    @NotNull
    private String returnDate;

    @NotNull
    private String returnTime;

    @NotNull
    private String pickupLocation;

    @NotNull
    private String returnLocation;

    @NotNull
    private String carId;

    @NotNull
    private String user;

    @NotNull
    private String rentACarId;

    public VehicleReservationDTO() {
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(String returnLocation) {
        this.returnLocation = returnLocation;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRentACarId() {
        return rentACarId;
    }

    public void setRentACarId(String rentACarId) {
        this.rentACarId = rentACarId;
    }
}
