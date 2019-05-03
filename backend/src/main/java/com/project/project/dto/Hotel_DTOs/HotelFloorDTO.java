package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelFloor;
import com.project.project.model.Hotel_Model.Room;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class HotelFloorDTO {
    private Long id;

    @NotNull
    private int level;

    @NotNull
    private int maxRooms;

    private Set<Room> roomsOnFloor;

    @NotNull
    private Hotel hotel;

    public HotelFloorDTO(){}

    public HotelFloorDTO(HotelFloor f) {
        this.id = f.getId();
        this.level = f.getLevel();
        this.maxRooms = f.getMaxRooms();
        this.roomsOnFloor = f.getRoomsOnFloor();
        this.hotel = f.getHotel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(int maxRooms) {
        this.maxRooms = maxRooms;
    }

    public Set<Room> getRoomsOnFloor() {
        return roomsOnFloor;
    }

    public void setRoomsOnFloor(Set<Room> roomsOnFloor) {
        this.roomsOnFloor = roomsOnFloor;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
