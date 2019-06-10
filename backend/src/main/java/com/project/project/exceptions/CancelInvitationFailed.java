package com.project.project.exceptions;

public class CancelInvitationFailed extends  Exception{

    public  CancelInvitationFailed() {
        super("You can't cancel invitation because it is less than 3 hours to flight!");
    }
}
