package com.project.project.exceptions;

public class DestinationAlreadyExist extends Exception {
    public DestinationAlreadyExist(String name, String zip) {
        super("Destination " + name + " " + zip + " already exist!");
    }
}
