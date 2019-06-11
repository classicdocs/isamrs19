package com.project.project.exceptions;

public class CancelReservationFailed extends Exception {

    public  CancelReservationFailed() {
        super("You can't cancel reservation because it is less than 3 hours to flight!");
    }
}
