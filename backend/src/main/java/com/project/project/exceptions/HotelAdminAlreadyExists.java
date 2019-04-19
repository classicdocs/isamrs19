package com.project.project.exceptions;

public class HotelAdminAlreadyExists extends Exception {
    public HotelAdminAlreadyExists(String username) {
        super("Hotel admin with username " + username + " already exist!");
    }
}
