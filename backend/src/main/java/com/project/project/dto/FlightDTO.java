package com.project.project.dto;

import com.project.project.model.Destination;
import com.project.project.model.Flight;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class FlightDTO {
    private Long id;
    private DestinationDTO startDestination;
    private DestinationDTO finalDestination;
    private String departureDate;
    private String landingDate;
    private String flightTime;
    private double distance;
    private int numOfTransfer;
    private Set<DestinationDTO> transfers = new HashSet<DestinationDTO>();
    private double ticketPrice;

    public FlightDTO() {

    }

    public FlightDTO(Flight flight) {
        this.id = flight.getId();
        this.startDestination = new DestinationDTO(flight.getStartDestination().getName());
        this.finalDestination = new DestinationDTO(flight.getFinalDestination().getName());
        this.departureDate = flight.getDepartureDate();
        this.landingDate = flight.getLandingDate();
        this.flightTime = flight.getFlightTime();
        this.distance = flight.getDistance();
        this.numOfTransfer = flight.getNumOfTransfer();

        for (Destination d: flight.getTransfers()) {
            this.transfers.add(new DestinationDTO(d.getName()));
        }

        this.ticketPrice = flight.getTicketPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DestinationDTO getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(DestinationDTO startDestination) {
        this.startDestination = startDestination;
    }

    public DestinationDTO getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(DestinationDTO finalDestination) {
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

    public Set<DestinationDTO> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<DestinationDTO> transfers) {
        this.transfers = transfers;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
