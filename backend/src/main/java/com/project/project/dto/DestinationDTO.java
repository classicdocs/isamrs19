package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class DestinationDTO {
    @NotNull
    private String name;

    public DestinationDTO() {

    }

    public DestinationDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
