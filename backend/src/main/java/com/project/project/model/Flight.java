package com.project.project.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AirlineCompany airlineCompany;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Destination startDestination;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Destination finalDestination;

    @Column(name = "departure_date", nullable = false)
    private String departureDate;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @Column(name = "landing_date", nullable = false)
    private String landingDate;

    @Column(name = "landing_time", nullable = false)
    private String landingTime;

    @Column(name = "flight_time", nullable = false)
    private String flightTime;

    @Column(name = "distance", nullable = false)
    private double distance;

    @ElementCollection
    @CollectionTable(name = "flight_transfers", joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "transfers")
    private Set<String> transfers = new HashSet<>();

    @Column(name = "ticket_price", nullable = false)
    private double ticketPrice;

    public Flight() {
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

    public Destination getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(Destination startDestination) {
        this.startDestination = startDestination;
    }

    public Destination getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(Destination finalDestination) {
        this.finalDestination = finalDestination;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    public String getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(String landingTime) {
        this.landingTime = landingTime;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Set<String> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<String> transfers) {
        this.transfers = transfers;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

