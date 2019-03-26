package com.project.project.model;

import java.util.List;

public class Flight {
    private String id;
    private Destination startDestination;
    private Destination finalDestination;
    private String departureDate;
    private String landingDate;
    private String flightTime;
    private double distance;
    private int numOfTransfer;
    private List<Destination> transfers;
    private double ticketPrice;

    public Flight() {
    }

    public Flight(String id, Destination startDestination, Destination finalDestination, String departureDate,
                  String landingDate, String flightTime, double distance, int numOfTransfer,
                  List<Destination> transfers, double ticketPrice) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<Destination> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Destination> transfers) {
        this.transfers = transfers;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
