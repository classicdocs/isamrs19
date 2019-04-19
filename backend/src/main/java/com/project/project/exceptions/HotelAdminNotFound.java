package com.project.project.exceptions;

public class HotelAdminNotFound extends Exception {

    public HotelAdminNotFound (String username) {
        super("Hotel administrator with username " + username + " doesn't exist!");
    }
}
