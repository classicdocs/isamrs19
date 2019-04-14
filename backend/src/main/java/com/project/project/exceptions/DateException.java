package com.project.project.exceptions;

public class DateException extends Exception{

    public DateException(String d1, String d2) {
        super("Date " + d1 + " can't be after date " + d2);
    }
}
