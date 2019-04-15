package com.project.project.exceptions;

public class UsernameNotFound extends Exception {
    public UsernameNotFound(String username) {
        super("User with username " + username + " does not exist.");
    }
}
