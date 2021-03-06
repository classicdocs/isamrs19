package com.project.project.dto;

import com.project.project.model.VehicleReservation;

import javax.validation.constraints.NotNull;

public class VehicleReservationDTO {

    private String carId;

    private Long id;

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

    private RentACarInfoDTO rentACar;

    private boolean completed;

    private boolean gpsIncluded;

    private boolean childSeatIncluded;

    private boolean collisionInsuranceIncluded;

    private boolean theftInsuranceIncluded;

    public VehicleReservationDTO() {
    }

    public VehicleReservationDTO(VehicleReservation vr, boolean isCompleted){

        this.id = vr.getId();
        this.reservedFrom = vr.getReservedFrom().toString();
        this.reservedUntil = vr.getReservedUntil().toString();

        this.vehicle = new VehicleDTO(vr.getVehicle());

        this.pickupLocation = vr.getPickupLocation();

        this.returnLocation = vr.getReturnLocation();

        this.rentACar = new RentACarInfoDTO(vr.getRentACar());

        this.user = vr.getUser().getUsername();

        this.totalPrice = String.valueOf(vr.getTotalPrice());

        this.completed = isCompleted;

        this.gpsIncluded = vr.isGpsIncluded();

        this.childSeatIncluded = vr.isChildSeatIncluded();

        this.collisionInsuranceIncluded = vr.isCollisionInsuranceIncluded();

        this.theftInsuranceIncluded = vr.isTheftInsuranceIncluded();
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

    public RentACarInfoDTO getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACarInfoDTO rentACar) {
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGpsIncluded() {
        return gpsIncluded;
    }

    public void setGpsIncluded(boolean gpsIncluded) {
        this.gpsIncluded = gpsIncluded;
    }

    public boolean isChildSeatIncluded() {
        return childSeatIncluded;
    }

    public void setChildSeatIncluded(boolean childSeatIncluded) {
        this.childSeatIncluded = childSeatIncluded;
    }

    public boolean isCollisionInsuranceIncluded() {
        return collisionInsuranceIncluded;
    }

    public void setCollisionInsuranceIncluded(boolean collisionInsuranceIncluded) {
        this.collisionInsuranceIncluded = collisionInsuranceIncluded;
    }

    public boolean isTheftInsuranceIncluded() {
        return theftInsuranceIncluded;
    }

    public void setTheftInsuranceIncluded(boolean theftInsuranceIncluded) {
        this.theftInsuranceIncluded = theftInsuranceIncluded;
    }
}
