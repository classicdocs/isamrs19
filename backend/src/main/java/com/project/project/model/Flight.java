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
    private Destination startDestination;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Destination finalDestination;

    @Column(name = "departureDate", nullable = false)
    private String departureDate;

    @Column(name = "landingDate", nullable = false)
    private String landingDate;

    @Column(name = "flightTime", nullable = false)
    private String flightTime;

    @Column(name = "distance", nullable = false)
    private double distance;

    @Column(name = "numOfTransfer", nullable = false)
    private int numOfTransfer;

    @ElementCollection
    @CollectionTable(name = "flight_transfers", joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "transfers")
    private Set<String> transfers = new HashSet<>();

    @Column(name = "ticketPrice", nullable = false)
    private double ticketPrice;

    public Flight() {
    }

    public Flight(Long id, Destination startDestination, Destination finalDestination, String departureDate, String landingDate, String flightTime, double distance, int numOfTransfer, Set<String> transfers, double ticketPrice) {
        this.id = id;
        this.startDestination = startDestination;
        this.finalDestination = finalDestination;
        this.departureDate = departureDate;
        this.landingDate = landingDate;
        this.flightTime = flightTime;
        this.distance = distance;
        this.numOfTransfer = numOfTransfer;
        this.transfers = transfers;
        this.ticketPrice = ticketPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
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

    public int getNumOfTransfer() {
        return numOfTransfer;
    }

    public void setNumOfTransfer(int numOfTransfer) {
        this.numOfTransfer = numOfTransfer;
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
