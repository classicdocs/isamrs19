package com.project.project.model.Hotel_Model;


import com.project.project.model.HotelAdmin;
import com.project.project.model.MapLocation;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "numOfFloors", nullable = false)
    private int numOfFloors;

    @Column(name = "roomsByFloor", nullable = false)
    private int roomsByFloor;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private MapLocation mapLocation;

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }
//    @Column(name = "latitude", nullable = false)
//    private double latitude;
//
//    @Column(name = "longitude", nullable = false)
//    private double longitude;


//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelsOffer> priceList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelFloor> floors;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelAdmin> admins;

    @ManyToOne
    @JoinColumn(name="destination_id", nullable=false)
    private HotelDestination destination;

    public HotelDestination getDestination() {
        return destination;
    }

    public void setDestination(HotelDestination destination) {
        this.destination = destination;
    }

    public Hotel(){}

    public int getNumOfFloors() {
        return numOfFloors;
    }

    public void setNumOfFloors(int numOfFloors) {
        this.numOfFloors = numOfFloors;
    }

    public int getRoomsByFloor() {
        return roomsByFloor;
    }

    public void setRoomsByFloor(int roomsByFloor) {
        this.roomsByFloor = roomsByFloor;
    }

    public Set<HotelAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<HotelAdmin> admins) {
        this.admins = admins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<HotelsOffer> getPriceList() {
        return priceList;
    }

    public void setPriceList(Set<HotelsOffer> priceList) {
        this.priceList = priceList;
    }

    public Set<HotelFloor> getFloors() {
        return floors;
    }

    public void setFloors(Set<HotelFloor> floors) {
        this.floors = floors;
    }

//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
}
