package com.project.project.exceptions;

public class FlightNotFound extends Exception {
    public FlightNotFound(Long id) {
        super("Flight with id " + id + " doesn't exist!");
    }
}
