package com.project.project.exceptions;

public class UsernameTaken extends Exception {
    public UsernameTaken() {
        super("Username is taken");
    }
}
