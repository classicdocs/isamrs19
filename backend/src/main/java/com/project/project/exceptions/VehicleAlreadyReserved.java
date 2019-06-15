package com.project.project.exceptions;

public class VehicleAlreadyReserved extends Exception {
    public VehicleAlreadyReserved() {
        super("Unfortunately,this vehicle is no longer available for the period you entered.");
    }
}
