package com.project.project.exceptions;

public class AlreadyFriend extends Exception {
    public AlreadyFriend(String username) {
        super("User with username " + username + " is already a friend!");
    }
}
