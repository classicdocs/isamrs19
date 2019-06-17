package com.project.project.model.Hotel_Model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "roomNumber", nullable = false)
    private int roomNumber;

    @Column(name = "numberOfBeds", nullable = false)
    private int numberOfBeds;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "room_booked",
            joinColumns = { @JoinColumn(name = "room_id", referencedColumnName = "id" )},
            inverseJoinColumns = { @JoinColumn(name = "room_taken_id", referencedColumnName = "id" ) })
    private Set<RoomTaken> roomTaken;

    //FIXME proveri ovu referencu V
    @ManyToOne(fetch = FetchType.EAGER)
    private HotelFloor hotelFloor;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<SpecialPrice> specialPrices;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<RoomDiscount> roomDiscounts;

    @Column(name = "average_rating")
    private double average_rating;

    @Column(name = "total_rating")
    private double total_rating;

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

    public Set<RoomTaken> getRoomTaken() {
        return roomTaken;
    }

    public void setRoomTaken(Set<RoomTaken> roomTaken) {
        this.roomTaken = roomTaken;
    }

    public HotelFloor getHotelFloor() {
        return hotelFloor;
    }

    public void setHotelFloor(HotelFloor hotelFloor) {
        this.hotelFloor = hotelFloor;
    }


    public Set<SpecialPrice> getSpecialPrices() {
        return specialPrices;
    }

    public void setSpecialPrices(Set<SpecialPrice> specialPrices) {
        this.specialPrices = specialPrices;
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

    public Set<RoomDiscount> getRoomDiscounts() {
        return roomDiscounts;
    }

    public void setRoomDiscounts(Set<RoomDiscount> roomDiscounts) {
        this.roomDiscounts = roomDiscounts;
    }
}
