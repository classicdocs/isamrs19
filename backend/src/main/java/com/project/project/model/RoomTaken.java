package com.project.project.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class RoomTaken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "endDaTe", nullable = false)
    private String endDaTe;

    //@ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "roomTaken")
    private Set<Room> rooms;


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

    public String getEndDaTe() {
        return this.endDaTe;
    }

    public void setEndDaTe(String endDaTe) {
        this.endDaTe = endDaTe;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }
}