package com.project.project.exceptions;

public class FlightInvitationNotFound extends Exception {
    public FlightInvitationNotFound(Long invitationId) {
        super("Flight invitation with id " + invitationId + " doesn't exist!");
    }
}
