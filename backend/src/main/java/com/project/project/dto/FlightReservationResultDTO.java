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

}
