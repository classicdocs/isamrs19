package com.project.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class VehicleQuickReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    @Column
    private Date reservedFrom;

    @Column
    private Date reservedUntil;

    @Column
    private String pickupLocation;

    @Column
    private String returnLocation;

    @Column
    private double totalPrice;

    @Column
    private boolean gpsIncluded;

    @Column
    private boolean childSeatIncluded;

    @Column
    private boolean collisionInsuranceIncluded;

    @Column
    private boolean theftInsuranceIncluded;

    public VehicleQuickReservation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getReservedFrom() {
        return reservedFrom;
    }

    public void setReservedFrom(Date reservedFrom) {
        this.reservedFrom = reservedFrom;
    }

    public Date getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(Date reservedUntil) {
        this.reservedUntil = reservedUntil;
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
}
