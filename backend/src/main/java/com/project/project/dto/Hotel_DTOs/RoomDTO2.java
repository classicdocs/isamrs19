package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class RoomDTO2 {

    @NotNull
    private Long id;

    @NotNull
    private int roomNumber;

    @NotNull
    private int numberOfBeds;

    @NotNull
    private HotelFloor hotelFloor;

    private Set<RoomTaken> roomTaken;

    private Set<SpecialPrice> specialPrices;

    @NotNull
    private double average_rating;

    @NotNull
    private double total_rating;

    public RoomDTO2(Room room) {
        this.roomNumber = room.getRoomNumber();
        this.numberOfBeds = room.getNumberOfBeds();
        this.id = room.getId();
        this.average_rating = room.getAverage_rating();
        this.total_rating = room.getTotal_rating();
    }

    public RoomDTO2(){}

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

    public HotelFloor getHotelFloor() {
        return hotelFloor;
    }

    public void setHotelFloor(HotelFloor hotelFloor) {
        this.hotelFloor = hotelFloor;
    }

    public Set<RoomTaken> getRoomTaken() {
        return roomTaken;
    }

    public void setRoomTaken(Set<RoomTaken> roomTaken) {
        this.roomTaken = roomTaken;
    }

    public Set<SpecialPrice> getSpecialPrices() {
        return specialPrices;
    }

    public void setSpecialPrices(Set<SpecialPrice> specialPrices) {
        this.specialPrices = specialPrices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
