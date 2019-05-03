package com.project.project.exceptions;

public class FriendshipWrongRole extends Exception {
    public FriendshipWrongRole(String role) {
        super("Wrong role " + role);
    }
}
