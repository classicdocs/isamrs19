package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelDestination;

import javax.validation.constraints.NotNull;

public class SearchHotelDTO {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String description;

    @NotNull
    private HotelDestinationDTO destination;

    public SearchHotelDTO() {
    }

    public SearchHotelDTO(HotelDTO hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
        this.destination = new HotelDestinationDTO();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HotelDestinationDTO getDestination() {
        return destination;
    }

    public void setDestination(HotelDestinationDTO destination) {
        this.destination = destination;
    }
}
