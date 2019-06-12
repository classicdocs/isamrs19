package com.project.project.dto.Hotel_DTOs;

import com.project.project.model.Hotel_Model.Hotel;
import com.project.project.model.Hotel_Model.HotelReservation;
import com.project.project.model.Hotel_Model.HotelsOffer;
import com.project.project.model.Hotel_Model.Room;

import java.util.Set;

public class HotelReservationDTO {

    private Long id;

    private String checkInDate;

    private String checkOutDate;

    private int numberOfPeople;

    private double totalPrice;

    private Set<Room> rooms;

    private Set<HotelsOffer> additionalServices;

    private String user;

    private Hotel hotel;

    public HotelReservationDTO(Long id, String checkInDate, String checkOutDate, int numberOfPeople, double totalPrice,
                               Set<Room> rooms, Set<HotelsOffer> additionalServices, String user, Hotel hotel) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = totalPrice;
        this.rooms = rooms;
        this.additionalServices = additionalServices;
        this.user = user;
        this.hotel = hotel;
    }

    public HotelReservationDTO() {
    }

    public HotelReservationDTO(HotelReservation reservation) {
        this.id = reservation.getId();
        this.checkInDate = reservation.getCheckInDate();
        this.checkOutDate = reservation.getCheckOutDate();
        this.numberOfPeople = reservation.getNumberOfPeople();
        this.totalPrice = reservation.getTotalPrice();
        this.rooms = reservation.getRooms();
        this.additionalServices = reservation.getAdditionalServices();
        this.user = reservation.getUser().getUsername();
        this.hotel = reservation.getHotel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<HotelsOffer> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<HotelsOffer> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
