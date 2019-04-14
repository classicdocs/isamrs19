package com.project.project.dto;

import com.project.project.model.Destination;

import javax.validation.constraints.NotNull;

public class DestinationDTO {
    @NotNull
    private String name;

    public DestinationDTO(Destination d) {
        this.name = d.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
