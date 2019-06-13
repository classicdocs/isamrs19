package com.project.project.exceptions;

public class LuggageAlreadyExist extends Exception {
    public LuggageAlreadyExist(String name) {
        super("Luggage with name " + name + " already exist");
    }
}
