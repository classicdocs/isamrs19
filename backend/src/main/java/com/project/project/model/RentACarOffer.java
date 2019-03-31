package com.project.project.model;

import javax.persistence.*;

@Entity
public class RentACarOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "offerName", nullable = false)
    private String offerName;

    @Column(name = "offerprice", nullable = false)
    private Double offerprice;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RentACar rentACarService;

    public RentACarOffer() {}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public RentACar getRentACarService() {
        return rentACarService;
    }

    public void setRentACarService(RentACar rentACarService) {
        this.rentACarService = rentACarService;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public Double getOfferprice() {
        return offerprice;
    }

    public void setOfferprice(Double offerprice) {
        this.offerprice = offerprice;
    }

    public RentACarOffer(Long id, String offerName, Double offerprice, RentACar rentACarService) {
        Id = id;
        this.offerName = offerName;
        this.offerprice = offerprice;
        this.rentACarService = rentACarService;
    }
}
