package com.project.project.dto;

import com.project.project.model.Room;

import javax.validation.constraints.NotNull;

public class RoomDTO {
    @NotNull
    private int roomNumber;

    private int numberOfBeds;

    public int getRoomNumber() {
        return roomNumber;
    }
    public RoomDTO(){}

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public RoomDTO(Room room) {
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
    }
}
