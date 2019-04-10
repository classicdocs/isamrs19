package com.project.project.exceptions;

public class AirplaneNotExist extends Exception {
    public AirplaneNotExist(String airplane, String airline_company) {
        super("Airplane " + airplane + " doesn't exist in " + airline_company);
    }
}
