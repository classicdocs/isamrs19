package com.project.project.exceptions;

public class HotelHasNoDestination extends Exception {
    public HotelHasNoDestination(String name) {
        super("Hotel with name " + name + " does not have a destination!");
    }

}