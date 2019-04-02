package com.project.project.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "roomNumber", nullable = false)
    private int roomNumber;

    @Column(name = "numberOfBeds", nullable = false)
    private int numberOfBeds;

    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "room_booked",
			joinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "Id" )},
			inverseJoinColumns = { @JoinColumn(name = "room_taken_id", referencedColumnName = "Id" ) })
    private List<RoomTaken> roomTaken;

    //FIXME proveri ovu referencu V
    @ManyToOne(fetch = FetchType.EAGER)
    private Hotel hotel;

	public Room(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
