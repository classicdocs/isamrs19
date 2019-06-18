package com.project.project.exceptions;

public class VehicleIsReserved extends Exception {
    public VehicleIsReserved() { super("You cannot edit/delete a reserved vehicle.");}
}
