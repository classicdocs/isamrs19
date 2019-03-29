package com.project.project.exceptions;

public class DestinationNotFound extends Exception {

    public DestinationNotFound(String name) {
        super("Destination with name " + name + "doesn't exist!");
    }
}
