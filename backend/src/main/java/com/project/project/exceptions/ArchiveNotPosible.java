package com.project.project.exceptions;

public class ArchiveNotPosible extends Exception {

    public ArchiveNotPosible() {
        super("You can't archive this flight because the plane hasn't landed yet");
    }
}
