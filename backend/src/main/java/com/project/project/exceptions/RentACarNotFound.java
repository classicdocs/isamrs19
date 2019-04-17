package com.project.project.exceptions;

public class RentACarNotFound extends Exception {

    public RentACarNotFound(Long id) {
        super("Rent-a-car service with id " + id + " doesn't exist!");
    }
}
