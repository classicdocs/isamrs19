package com.project.project.dto;

import com.project.project.model.Flight;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.HashSet;
import java.util.Set;

public class FlightDTO {
    private Long id;
    @NotNull
    private String airlineCompany;
    @NotNull
    private AirplaneDTO airplane;
    @NotNull
    private String startDestination;
    @NotNull
    private String finalDestination;
    @NotNull
    private String departureDate;
    @NotNull
    private String departureTime;
    @NotNull
    private String landingDate;
    @NotNull
    private String landingTime;
    @Positive
    private int flightTimeHours;
    @Positive
    private int flightTimeMinutes;
    @Positive
    private double distance;
    private Set<String> transferDestinations;
    @Positive
    private double ticketPriceFirst;
    @Positive
    private double ticketPriceBuissness;
    @Positive
    private double ticketPriceEconomy;

    public FlightDTO() {
    }

    public FlightDTO(@NotNull Flight flight) {
        this.id = flight.getId();
;        this.airlineCompany = flight.getAirlineCompany().getId().toString();
        this.airplane = new AirplaneDTO();
        this.airplane.setId(flight.getAirplane().getId());
        this.airplane.setModel(flight.getAirplane().getModel());
        this.startDestination = flight.getStartDestination().getName();
        this.finalDestination = flight.getFinalDestination().getName();
        this.departureDate = flight.getDepartureDate();
        this.departureTime = flight.getDepartureTime();
        this.landingDate = flight.getLandingDate();
        this.landingTime = flight.getLandingTime();
        this.flightTimeHours = flight.getFlightTimeHours();
        this.flightTimeMinutes = flight.getFlightTimeMinutes();
        this.distance = flight.getDistance();
        this.transferDestinations = flight.getTransferDestinations();
        this.ticketPriceFirst = flight.getTicketPriceFirst();
        this.ticketPriceBuissness = flight.getTicketPriceBuisness();
        this.ticketPriceEconomy = flight.getTicketPriceEconomy();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(String airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public AirplaneDTO getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getFinalDestination() {
        return finalDestination;
    }

    public void setFinalDestination(String finalDestination) {
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

    public int getFlightTimeHours() {
        return flightTimeHours;
    }

    public void setFlightTimeHours(int flightTimeHours) {
        this.flightTimeHours = flightTimeHours;
    }

    public int getFlightTimeMinutes() {
        return flightTimeMinutes;
    }

    public void setFlightTimeMinutes(int flightTimeMinutes) {
        this.flightTimeMinutes = flightTimeMinutes;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Set<String> getTransferDestinations() {
        return transferDestinations;
    }

    public void setTransferDestinations(Set<String> transferDestinations) {
        this.transferDestinations = transferDestinations;
    }

    public double getTicketPriceFirst() {
        return ticketPriceFirst;
    }

    public void setTicketPriceFirst(double ticketPriceFirst) {
        this.ticketPriceFirst = ticketPriceFirst;
    }

    public double getTicketPriceBuissness() {
        return ticketPriceBuissness;
    }

    public void setTicketPriceBuissness(double ticketPriceBuissness) {
        this.ticketPriceBuissness = ticketPriceBuissness;
    }

    public double getTicketPriceEconomy() {
        return ticketPriceEconomy;
    }

    public void setTicketPriceEconomy(double ticketPriceEconomy) {
        this.ticketPriceEconomy = ticketPriceEconomy;
    }
}
