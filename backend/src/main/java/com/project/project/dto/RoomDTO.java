package com.project.project.dto;

import java.util.List;
import com.project.project.model.Hotel;
import com.project.project.model.RoomTaken;
import com.project.project.model.Room;


public class RoomDTO{
    private int roomNumber;
    private int numberOfBeds;
    private List<RoomTaken> roomTaken;
    private Hotel hotel;

    public RoomDTO(){}
    
	public RoomDTO(Room room){
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.roomTaken = room.getRoomTaken();
        this.hotel = room.getHotel();
    }

	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getNumberOfBeds() {
		return this.numberOfBeds;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public List<RoomTaken> getRoomTaken() {
		return this.roomTaken;
	}

	public void setRoomTaken(List<RoomTaken> roomTaken) {
		this.roomTaken = roomTaken;
	}

	public Hotel getHotel() {
		return this.hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}



}