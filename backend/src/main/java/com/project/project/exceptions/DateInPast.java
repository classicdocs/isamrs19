package com.project.project.exceptions;

public class DateInPast extends Exception {
    public DateInPast() {
        super("You chose a date in the past. Please choose a valid date.");
    }
}
