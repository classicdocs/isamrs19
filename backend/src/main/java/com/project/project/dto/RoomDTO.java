package com.project.project.dto;

import java.util.List;
import com.project.project.model.Hotel;
import com.project.project.model.RoomTaken;
import com.project.project.model.Room;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class RoomDTO{

	@Positive
	private int Id;
	@PositiveOrZero
    private int roomNumber;
    @Positive
	private int numberOfBeds;
    @NotNull
    private List<RoomTaken> roomTaken;
    @NotNull
    private Hotel hotel;

	public RoomDTO(Room room) {
		Id = room.getId();
		this.roomNumber = room.getRoomNumber();
		this.numberOfBeds = room.getNumberOfBeds();
		this.roomTaken = room.getRoomTaken();
		this.hotel = room.getHotel();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public RoomDTO(){}


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