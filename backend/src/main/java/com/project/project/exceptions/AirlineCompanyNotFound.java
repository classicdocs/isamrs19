package com.project.project.exceptions;

public class AirlineCompanyNotFound extends Exception {

    public AirlineCompanyNotFound(Long id) {
        super("Airline company with id " + id + " doesn't exist!");
    }
}
