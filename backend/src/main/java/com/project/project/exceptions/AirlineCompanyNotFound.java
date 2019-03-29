package com.project.project.exceptions;

public class AirlineCompanyNotFound extends Exception {

    public AirlineCompanyNotFound(String name) {
        super("Airline company with name " + name + " doesn't exist!");
    }
}
