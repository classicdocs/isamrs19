package com.project.project.exceptions;

public class ReturnDateBeforePickupDate extends Exception {
    public ReturnDateBeforePickupDate() {
        super("Pickup date can't be before return date!");
    }
}
