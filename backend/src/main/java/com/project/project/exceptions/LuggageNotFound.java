package com.project.project.exceptions;

public class LuggageNotFound extends Exception {
    public LuggageNotFound(Long luggageId) {
        super("Luggage with id " + luggageId + " doesn't exist");
    }
}
