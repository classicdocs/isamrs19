package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class FriendshipDTO {

    @NotNull
    private Long from;

    @NotNull
    private Long to;

    public FriendshipDTO() {

    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }
}
