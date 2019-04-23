package com.project.project.exceptions;

public class UserNotLoggedFirstTime extends Exception {

    public UserNotLoggedFirstTime(Long id) {
        super("User with id "+ id + " is not logged in for the first time" );
    }
}
