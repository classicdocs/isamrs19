package com.project.project.exceptions;

public class RentACarAlreadyExist extends Exception {
    public RentACarAlreadyExist(String name) {
        super("Rent a car service with name " + name + " already exist!");
    }
}