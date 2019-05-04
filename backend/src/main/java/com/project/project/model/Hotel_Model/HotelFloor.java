package com.project.project.model.Hotel_Model;

import com.project.project.exceptions.HotelAlreadyExists;

import javax.persistence.*;
import java.util.Set;

/*Klasa predstavlja sprat u hotelu, ima svoj nivo, maksimalni moguci broj soba, i set soba napravljenih na tom spratu*/
@Entity
public class HotelFloor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level", nullable = false)
    private int level;

    @Column(name = "maxRooms", nullable = false)
    private int maxRooms;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)   // to one - EAGER, to many - LAZY
    private Set<Room> roomsOnFloor;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Hotel hotel;

    public HotelFloor() {}


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
}
