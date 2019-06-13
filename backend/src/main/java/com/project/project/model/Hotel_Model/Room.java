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

    @Column(name = "average_rating")
    private double averageRating;

    @Column(name = "total_rating")
    private double totalRating;

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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }
}
