package com.project.project.model;

import javax.persistence.*;

@Entity
public class HotelAdmin extends User{

    @ManyToOne
    private Hotel hotel;

    public HotelAdmin() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
