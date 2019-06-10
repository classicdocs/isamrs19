package com.project.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FlightReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    private Flight departureFlight;

    @ManyToOne(fetch = FetchType.EAGER)
    private Flight returnFlight;

    @OneToMany(mappedBy = "flightReservation", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    @ManyToOne
    private AirlineCompany airlineCompany;

    @Column(nullable = false)
    private double pricePerPerson;

    @ManyToOne
    private RegisteredUser user;

    public FlightReservation() {
        this.passengers = new HashSet<Passenger>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flight getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(Flight departureFlight) {
        this.departureFlight = departureFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        this.user = user;
    }
}
