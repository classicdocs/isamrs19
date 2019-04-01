package com.project.project.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@Column(name = "roomNumber", nullable = false)
    private int roomNumber;

    @Column(name = "numberOfBeds", nullable = false)
    private int numberOfBeds;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoomTaken> roomTaken;

    @OneToMany(fetch = FetchType.EAGER)
    private Hotel hotel;

	public Room(){}

	public Room(int id, int roomNumber, int numberOfBeds, List<RoomTaken> roomTaken, Hotel hotel) {
		Id = id;
		this.roomNumber = roomNumber;
		this.numberOfBeds = numberOfBeds;
		this.roomTaken = roomTaken;
		this.hotel = hotel;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

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

	public List<RoomTaken> getRoomTaken() {
		return roomTaken;
	}

	public void setRoomTaken(List<RoomTaken> roomTaken) {
		this.roomTaken = roomTaken;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
