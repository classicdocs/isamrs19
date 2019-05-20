package com.project.project.model.Hotel_Model;
import javax.persistence.*;
@Entity
public class HotelsOffer {
    /*
     * Ova klasa predstavlja uslugu hotela. Usluga moze da bude nocenje ili neka prethodno definisana
     * dodatna usluga
     * dodatne usluge mogu biti transfer od/do aerodroma,
     * parking, koriscenje bazena, restoran, sobni servis, welness, spa centar, WiFi, itd.
     * */

    public enum offerType {OneBed (0),
        TwoBeds (1),
        ThreeBeds (2),
        FourBeds (3),
        FiveBeds (4),
        AdditionalService (5);

        private final int value;
        private offerType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "type", nullable = false)
    private offerType type;


    @Column(name = "description", nullable = true)
    private String description;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Hotel hotel;

//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }

    public HotelsOffer() {}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public offerType getType() {
        return type;
    }

    public void setType(offerType type) {

        this.type = type;
    }

}
