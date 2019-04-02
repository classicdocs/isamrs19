package com.project.project.exceptions;

public class AirlineCompanyAlreadyExist extends Exception {
    public AirlineCompanyAlreadyExist(String name) {
        super("Airline company with name " + name + " already exist!");
    }
}
