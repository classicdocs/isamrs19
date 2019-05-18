package com.project.project.model.Hotel_Model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class RoomTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "endDate", nullable = false)
    private String endDate;

//    @ManyToMany(fetch = FetchType.LAZY,  mappedBy = "roomTaken")
//    private Set<Room> rooms;


    public RoomTaken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    public Set<Room> getRooms() {
//        return rooms;
//    }
//
//    public void setRooms(Set<Room> rooms) {
//        this.rooms = rooms;
//    }
}