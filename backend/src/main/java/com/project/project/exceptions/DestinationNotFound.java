package com.project.project.exceptions;

public class DestinationNotFound extends Exception {

    public DestinationNotFound(String name, String zip) {
        super("Destination  " + name + " " + zip + " doesn't exist!");
    }
}
