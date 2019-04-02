package com.project.project.dto;

import com.project.project.model.Hotel;
import com.project.project.model.HotelAdmin;

import javax.validation.constraints.NotNull;

public class HotelAdminDTO {
    private Long ID;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private Hotel hotel;

    public HotelAdminDTO(HotelAdmin hotelAdmin) {
        this.ID = hotelAdmin.getID();
        this.username = hotelAdmin.getUsername();
        this.password = hotelAdmin.getPassword();
        this.hotel = hotelAdmin.getHotel();
    }

    public HotelAdminDTO() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
