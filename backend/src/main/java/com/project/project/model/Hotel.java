package com.project.project.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelsOffer> priceList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)   // to one - EAGER, to many - LAZY
    private Set<Room> roomConfiguration;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<HotelAdmin> admins;
    
    public Hotel(){}

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

    public Set<Room> getRoomConfiguration() {
        return roomConfiguration;
    }

    public void setRoomConfiguration(Set<Room> roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }

}
