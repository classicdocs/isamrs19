package com.project.project.exceptions;

public class UserNotVerified extends Exception {
    public UserNotVerified() { super("This account is not verified. " +
            "Please go to your e-mail and confirm your registration");}
}
