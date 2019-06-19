package com.project.project.exceptions;

public class UnableToDeleteRoom extends Exception {
    public UnableToDeleteRoom(Long roomID) { super("Unable to delete room with id " + roomID + ", because it has reservations");}
}
