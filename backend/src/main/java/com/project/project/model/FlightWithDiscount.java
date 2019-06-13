package com.project.project.model;

import javax.persistence.*;

@Entity
public class FlightWithDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Flight flight;

    @OneToOne
    private Seat seat;

    @Column(name = "price")
    private double price;

    @Column(name = "taken")
    private boolean taken;

    @ManyToOne
    private AirlineCompany airlineCompany;

    public FlightWithDiscount() {

    }

    public FlightWithDiscount(Long id, Flight flight, Seat seat, double price) {
        this.id = id;
        this.flight = flight;
        this.seat = seat;
        this.price = price;
        this.taken = false;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
}
