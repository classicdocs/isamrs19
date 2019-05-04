package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class VehicleSearchDTO {

    @NotNull
    private String pickupDate;

    @NotNull
    private String pickupTime;

    @NotNull
    private String returnDate;

    @NotNull
    private String returnTime;

    @NotNull
    private String carType;

    @NotNull
    private String passengers;

    @NotNull
    private String maxPrice;

    @NotNull
    private String minPrice;

    @NotNull
    private Long rentACar;

    public Long getRentACar() {
        return rentACar;
    }

    public void setRentACar(Long rentACar) {
        this.rentACar = rentACar;
    }

    public VehicleSearchDTO() {
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }
}
