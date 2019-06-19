package com.project.project.dto;

public class NewVehicleQuickReservationDTO {

    private String pickupDate;

    private String pickupTime;

    private String returnDate;

    private String returnTime;

    private String vehicle;

    private String pickupLocation;

    private String returnLocation;

    private double totalPrice;

    private boolean gpsIncluded;

    private boolean childSeatIncluded;

    private boolean collisionInsuranceIncluded;

    private boolean theftInsuranceIncluded;

    private String rentACarId;

    public NewVehicleQuickReservationDTO() {
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

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getRentACarId() {
        return rentACarId;
    }

    public void setRentACarId(String rentACarId) {
        this.rentACarId = rentACarId;
    }
}
