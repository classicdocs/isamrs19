package com.project.project.exceptions;

public class AdminAlreadyExists extends Exception {
    public AdminAlreadyExists(String username) {
        super("Admin with username " + username + " already exist!");
    }
}


