
package com.project.project.exceptions;

public class HotelNotFound extends Exception {

    public HotelNotFound(Long id) {
        super("Hotel with id " + id + " doesn't exist!");
    }
}
