
package com.project.project.exceptions;

public class FloorNotFound extends Exception {

    public FloorNotFound(Long id) {
        super("Floor with id " + id + " doesn't exist!");
    }
}
