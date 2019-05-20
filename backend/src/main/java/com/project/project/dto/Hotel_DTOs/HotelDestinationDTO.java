package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.HotelDestination;

import javax.validation.constraints.NotNull;

public class HotelDestinationDTO {
    @NotNull
    private String name;

    public HotelDestinationDTO(HotelDestination d) {
        this.name = d.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
