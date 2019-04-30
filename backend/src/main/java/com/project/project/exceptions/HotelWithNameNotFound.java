package com.project.project.exceptions;

public class HotelWithNameNotFound extends Exception {
    public HotelWithNameNotFound(String name) {
        super("Hotel with name " + name + " doesn't exist!");
    }
}
