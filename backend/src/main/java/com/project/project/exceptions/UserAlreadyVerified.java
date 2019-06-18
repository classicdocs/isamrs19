package com.project.project.exceptions;

public class UserAlreadyVerified extends Exception {
    public UserAlreadyVerified() { super("This account has already been verified.");}
}
