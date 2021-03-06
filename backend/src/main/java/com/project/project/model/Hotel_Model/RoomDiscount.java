package com.project.project.model.Hotel_Model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class RoomDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "startDate", nullable = false)
    private String startDate;

    @Column(name = "endDate", nullable = false)
    private String endDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HotelsOffer> additionalServices;

    public RoomDiscount() {}

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Set<HotelsOffer> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<HotelsOffer> additionalServices) {
        this.additionalServices = additionalServices;
    }
}
