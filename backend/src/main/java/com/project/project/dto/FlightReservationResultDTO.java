package com.project.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.project.model.Flight;
import com.project.project.model.FlightReservation;
import com.project.project.model.Passenger;

import java.util.Set;

public class FlightReservationResultDTO {

    private Long id;

    private String date;

    private FlightDTO departureFlight;

    private FlightDTO returnFlight;

    private Set<PassengerDTO> passengers;

    private double pricePerPerson;

    private boolean completed;

    private boolean hasReturnFlight;

    private boolean sameCompanies;

    public  FlightReservationResultDTO() {

    }

    public FlightReservationResultDTO(FlightReservation flightReservation, Set<PassengerDTO> passengers) {
        this.id = flightReservation.getId();
        this.date = flightReservation.getDate();
        this.departureFlight = new FlightDTO(flightReservation.getDepartureFlight());
        if (flightReservation.getReturnFlight() != null)
            this.returnFlight = new FlightDTO(flightReservation.getReturnFlight());
        else
            this.returnFlight = null;
        this.passengers = passengers;
        this.pricePerPerson = flightReservation.getPricePerPerson();
    }

    public FlightReservationResultDTO(FlightReservation flightReservation, Set<PassengerDTO> passengers,
                                      boolean completed, boolean hasReturnFlight, boolean sameCompanies) {
        this.id = flightReservation.getId();
        this.date = flightReservation.getDate();
        this.departureFlight = new FlightDTO(flightReservation.getDepartureFlight());
        if (flightReservation.getReturnFlight() != null)
            this.returnFlight = new FlightDTO(flightReservation.getReturnFlight());
        else
            this.returnFlight = null;
        this.passengers = passengers;
        this.pricePerPerson = flightReservation.getPricePerPerson();
        this.completed = completed;
        this.hasReturnFlight = hasReturnFlight;
        this.sameCompanies = sameCompanies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public FlightDTO getDepartureFlight() {
        return departureFlight;
    }

    public void setDepartureFlight(FlightDTO departureFlight) {
        this.departureFlight = departureFlight;
    }

    public FlightDTO getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(FlightDTO returnFlight) {
        this.returnFlight = returnFlight;
    }

    public Set<PassengerDTO> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<PassengerDTO> passengers) {
        this.passengers = passengers;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isHasReturnFlight() {
        return hasReturnFlight;
    }

    public void setHasReturnFlight(boolean hasReturnFlight) {
        this.hasReturnFlight = hasReturnFlight;
    }

    public boolean isSameCompanies() {
        return sameCompanies;
    }

    public void setSameCompanies(boolean sameCompanies) {
        this.sameCompanies = sameCompanies;
    }
}
