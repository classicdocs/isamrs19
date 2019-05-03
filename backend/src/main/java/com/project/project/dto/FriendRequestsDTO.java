package com.project.project.dto;

import java.util.HashSet;
import java.util.Set;

public class FriendRequestsDTO {

    private Set<FriendDTO> requestsFrom = new HashSet<>();

    private Set<FriendDTO> requestsTo = new HashSet<>();

    public FriendRequestsDTO() {


    }

    public Set<FriendDTO> getRequestsFrom() {
        return requestsFrom;
    }

    public void setRequestsFrom(Set<FriendDTO> requestsFrom) {
        this.requestsFrom = requestsFrom;
    }

    public Set<FriendDTO> getRequestsTo() {
        return requestsTo;
    }

    public void setRequestsTo(Set<FriendDTO> requestsTo) {
        this.requestsTo = requestsTo;
    }
}
