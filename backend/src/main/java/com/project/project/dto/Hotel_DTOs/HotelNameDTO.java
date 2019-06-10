package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.Hotel;

public class HotelNameDTO {
    private String name;

    public HotelNameDTO(Hotel hotel) {
        this.name = hotel.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
