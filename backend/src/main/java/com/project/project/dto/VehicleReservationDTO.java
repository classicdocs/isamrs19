package com.project.project.dto;

import com.project.project.model.VehicleReservation;

import javax.validation.constraints.NotNull;

public class VehicleReservationDTO {

    private String carId;


    private String pickupDate;

    private String pickupTime;

    private String returnDate;

    private String returnTime;

    private String reservedFrom;

    private String reservedUntil;

    @NotNull
    private String pickupLocation;

    @NotNull
    private String returnLocation;

    private VehicleDTO vehicle;

    private String totalPrice;

    @NotNull
    private String user;


    private String rentACarId;

    private RentACarDTO rentACar;

    public VehicleReservationDTO() {
    }

    public VehicleReservationDTO(VehicleReservation vr){

        this.reservedFrom = vr.getReservedFrom().toString();
        this.reservedUntil = vr.getReservedUntil().toString();

        this.vehicle = new VehicleDTO(vr.getVehicle());

        this.pickupLocation = vr.getPickupLocation();

        this.returnLocation = vr.getReturnLocation();

        this.rentACar = new RentACarDTO(vr.getRentACar());

        this.user = vr.getUser().getUsername();

        this.totalPrice = String.valueOf(vr.getTotalPrice());
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

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicleDTO) {
        this.vehicle = vehicleDTO;
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

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public RentACarDTO getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACarDTO rentACar) {
        this.rentACar = rentACar;
    }

    public String getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(String reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public String getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(String reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
