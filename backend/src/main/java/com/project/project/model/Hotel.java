package com.project.project.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HotelsOffer> priceList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Room> roomConfiguration;

    
    public Hotel(){}

    public Hotel(Long id, String name, String address,
                 String description, List<HotelsOffer> priceList, List<Room> roomConfiguration) {
        Id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.priceList = priceList;
        this.roomConfiguration = roomConfiguration;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public List<HotelsOffer> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<HotelsOffer> priceList) {
        this.priceList = priceList;
    }

    public List<Room> getRoomConfiguration() {
        return roomConfiguration;
    }

    public void setRoomConfiguration(List<Room> roomConfiguration) {
        this.roomConfiguration = roomConfiguration;
    }
}
