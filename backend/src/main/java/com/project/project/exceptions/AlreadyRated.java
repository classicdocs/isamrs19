package com.project.project.exceptions;

public class AlreadyRated extends Exception {

    public AlreadyRated() {
        super("You have already rated this service.");
    }
}
