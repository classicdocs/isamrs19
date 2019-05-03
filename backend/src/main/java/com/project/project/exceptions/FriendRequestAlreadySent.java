package com.project.project.exceptions;

public class FriendRequestAlreadySent extends Exception {
    public  FriendRequestAlreadySent(String username) {
        super("You already sent friend request to user " + username);
    }
}
