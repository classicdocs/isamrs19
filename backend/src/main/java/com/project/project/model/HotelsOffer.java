package com.project.project.model;

import javax.persistence.*;

public class HotelsOffer {
    /*
    * Ova klasa predstavlja uslugu hotela. Usluga moze da bude nocenje ili neka prethodno definisana
    * dodatna usluga
    * dodatne usluge mogu biti transfer od/do aerodroma,
    * parking, koriscenje bazena, restoran, sobni servis, welness, spa centar, WiFi, itd.
    * */

    private enum offerType {OnePersonNightStand,
                            TwoPersonNightStand,
                            ThreePersonNightStand,
                            FourPersonNightStand,
                            FivePersonNightStand,
                            AdditionalService}



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "type", nullable = false)
    private offerType type;

    public HotelsOffer() {    }

    public HotelsOffer(Long id, double price, offerType type) {
        this.id = id;
        this.price = price;
        this.type = type;
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
