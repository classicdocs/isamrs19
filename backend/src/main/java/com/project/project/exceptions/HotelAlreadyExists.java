package com.project.project.exceptions;

public class HotelAlreadyExists extends Exception {
    public HotelAlreadyExists(String name) {
        super("Hotel with name " + name + " already exist!");
    }
}
