package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.HotelFloor;
import com.project.project.model.Hotel_Model.Room;
import com.project.project.model.Hotel_Model.RoomTaken;
import com.project.project.model.Hotel_Model.SpecialPrice;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class RoomDTO {

    @NotNull
    private Long id;


    @NotNull
    private int roomNumber;

    @NotNull
    private int numberOfBeds;

    @NotNull
    private HotelFloor hotelFloor;

    private Set<RoomTaken> roomTaken;

    private Set<SpecialPrice> specialPrices;

    public RoomDTO(Room room) {
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.id = room.getId();
    }

    public RoomDTO(){}

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public HotelFloor getHotelFloor() {
        return hotelFloor;
    }

    public void setHotelFloor(HotelFloor hotelFloor) {
        this.hotelFloor = hotelFloor;
    }

    public Set<RoomTaken> getRoomTaken() {
        return roomTaken;
    }

    public void setRoomTaken(Set<RoomTaken> roomTaken) {
        this.roomTaken = roomTaken;
    }

    public Set<SpecialPrice> getSpecialPrices() {
        return specialPrices;
    }

    public void setSpecialPrices(Set<SpecialPrice> specialPrices) {
        this.specialPrices = specialPrices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
