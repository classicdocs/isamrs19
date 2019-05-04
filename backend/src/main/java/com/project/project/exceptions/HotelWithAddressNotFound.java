package com.project.project.exceptions;

public class HotelWithAddressNotFound extends Exception {
    public HotelWithAddressNotFound(String cityName) {
        super("There are no hotels in " + cityName + ".");
    }
}
