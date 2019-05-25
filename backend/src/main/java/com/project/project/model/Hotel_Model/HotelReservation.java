package com.project.project.model.Hotel_Model;


import com.project.project.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HotelReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String checkInDate;

    @Column(nullable = false)
    private String checkOutDate;

    @Column(nullable = false)
    private int numberOfPeople;

    @Column(nullable = false)
    private double totalPrice;

//    @OneToMany(fetch = FetchType.EAGER)
//    private Set<Room> rooms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rooms_reservations",
            joinColumns = { @JoinColumn(name = "reservation_id", referencedColumnName = "id" )},
            inverseJoinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id" ) })
    private Set<Room> rooms;


//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<HotelsOffer> additionalServices;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "additonal_services_reservations",
            joinColumns = { @JoinColumn(name = "reservation_id", referencedColumnName = "id" )},
            inverseJoinColumns = { @JoinColumn(name = "additional_service_id", referencedColumnName = "id" ) })
    private Set<HotelsOffer> additionalServices;


    @ManyToOne
    private User user;


    public HotelReservation() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
