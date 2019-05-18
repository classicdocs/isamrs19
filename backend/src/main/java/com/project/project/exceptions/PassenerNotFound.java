package com.project.project.exceptions;

public class PassenerNotFound extends Exception {
    public PassenerNotFound(Long userId) {
        super("Passenger with id " + userId + " not found!");
    }
}
