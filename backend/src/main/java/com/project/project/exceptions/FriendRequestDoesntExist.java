package com.project.project.exceptions;

public class FriendRequestDoesntExist extends Exception {
    public  FriendRequestDoesntExist(Long id) {
        super("Friend request to user " + id + " doesn't exist");
    }
}
