package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.HotelAdmin;
import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelDestination;
import com.project.project.model.Hotel_Model.HotelFloor;
import com.project.project.model.Hotel_Model.HotelsOffer;
import com.project.project.model.MapLocation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

public class HotelDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String description;

    @NotNull
    private Set<HotelsOffer> priceList;

    @NotNull
    private Set<HotelFloor> floors;

    @NotNull
    private Set<HotelAdmin> admins;

    @NotNull
    private HotelDestination destination;

    @NotNull
    private int numOfFloors;

    @NotNull
    private int roomsByFloor;

    @NotNull
    private double average_rating;
    @NotNull
    private MapLocation mapLocation;

    @NotNull
    private double total_rating;

    public HotelDTO(){}

    public HotelDTO(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.description = hotel.getDescription();
        this.priceList = hotel.getPriceList();
        this.floors = hotel.getFloors();
        this.admins = hotel.getAdmins();
        this.numOfFloors = hotel.getNumOfFloors();
        this.roomsByFloor = hotel.getRoomsByFloor();
        this.destination = hotel.getDestination();
        this.average_rating = hotel.getAverage_rating();
        this.total_rating = hotel.getTotal_rating();
        this.mapLocation = hotel.getMapLocation();
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

    public HotelDestination getDestination() {
        return destination;
    }

    public void setDestination(HotelDestination destination) {
        this.destination = destination;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(double average_rating) {
        this.average_rating = average_rating;
    }

    public double getTotal_rating() {
        return total_rating;
    }

    public void setTotal_rating(double total_rating) {
        this.total_rating = total_rating;
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }
}