package com.project.project.dto;

import com.project.project.dto.Hotel_DTOs.RoomDTO;

import javax.validation.constraints.NotNull;

public class Floor_RoomDTO {

    @NotNull
    private Long floorID;

    @NotNull
    private RoomDTO newRoom;

    public Floor_RoomDTO(@NotNull Long floorID, @NotNull RoomDTO newRoom) {
        this.floorID = floorID;
        this.newRoom = newRoom;
    }

    public Floor_RoomDTO() {
    }

    public Long getFloorID() {
        return floorID;
    }

    public void setFloorID(Long floorID) {
        this.floorID = floorID;
    }

    public RoomDTO getNewRoom() {
        return newRoom;
    }

    public void setNewRoom(RoomDTO newRoom) {
        this.newRoom = newRoom;
    }
}
