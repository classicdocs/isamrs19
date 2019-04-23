package com.project.project.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(Long id) {
        super("User with id " + id + " doesn't exist");
    }
}
