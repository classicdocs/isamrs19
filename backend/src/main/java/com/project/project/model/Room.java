package com.project.project.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roomNumber;

    @Column(name = "numberOfBeds", nullable = false)
    private int numberOfBeds;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoomTaken> roomTaken;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Hotel hotel;

	public Room(){}

    public Room(int roomNumber, int numberOfBeds, List<RoomTaken> roomTaken, Hotel hotel){
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.roomTaken = roomTaken;
        this.hotel = hotel;
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
		
	public int getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
}
