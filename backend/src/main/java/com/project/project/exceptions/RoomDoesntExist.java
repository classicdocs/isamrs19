
package com.project.project.exceptions;

public class RoomDoesntExist extends Exception {

    public RoomDoesntExist(Long id) {
        super("Room with id " + id + " doesn't exist!");
    }
}
