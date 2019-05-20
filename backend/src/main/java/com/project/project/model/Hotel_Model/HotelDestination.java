package com.project.project.model.Hotel_Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class HotelDestination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hotels_destinations",
            joinColumns = { @JoinColumn(name = "destination_id", referencedColumnName = "id" )},
            inverseJoinColumns = { @JoinColumn(name = "hotel_id", referencedColumnName = "id" ) })
    private Set<Hotel> hotels;

    @Column(name = "zip", nullable = false)
    private String zip;

    public HotelDestination() {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(Set<Hotel> hotels) {
        this.hotels = hotels;
    }
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
