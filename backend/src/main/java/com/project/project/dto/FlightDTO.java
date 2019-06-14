package com.project.project.dto;

import com.project.project.model.Destination;
import com.project.project.model.Flight;
import com.project.project.model.SeatRow;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.*;

public class FlightDTO {
    private Long id;
    @NotNull
    private AirlineCompanyDTO airlineCompany;
    @NotNull
    private AirplaneDTO airplane;
    @NotNull
    private Destination startDestination;
    @NotNull
    private Destination finalDestination;
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
    private double ticketPriceBusiness;
    @Positive
    private double ticketPriceEconomy;

    private boolean archived;

    private double averageRating;

    private List<SeatRowDTO> seatsFirst = new ArrayList<SeatRowDTO>();

    private List<SeatRowDTO> seatsBusiness = new ArrayList<SeatRowDTO>();

    private List<SeatRowDTO> seatsEconomy = new ArrayList<SeatRowDTO>();

    public FlightDTO() {
    }

    public FlightDTO(@NotNull Flight flight) {
        this.id = flight.getId();
        this.airlineCompany = new AirlineCompanyDTO(flight.getAirlineCompany());
        this.airplane = new AirplaneDTO();
        this.airplane.setId(flight.getAirplane().getId());
        this.airplane.setModel(flight.getAirplane().getModel());
        this.startDestination = flight.getStartDestination();
        this.finalDestination = flight.getFinalDestination();
        this.departureDate = flight.getDepartureDate();
        this.departureTime = flight.getDepartureTime();
        this.landingDate = flight.getLandingDate();
        this.landingTime = flight.getLandingTime();
        this.flightTimeHours = flight.getFlightTimeHours();
        this.flightTimeMinutes = flight.getFlightTimeMinutes();
        this.distance = flight.getDistance();
        this.transferDestinations = flight.getTransferDestinations();
        this.ticketPriceFirst = flight.getTicketPriceFirst();
        this.ticketPriceBusiness = flight.getTicketPriceBusiness();
        this.ticketPriceEconomy = flight.getTicketPriceEconomy();
        this.archived = flight.isArchived();
        this.averageRating = flight.getAverageRating();

        for (SeatRow sr: flight.getSeatsFirst()) {
                this.seatsFirst.add(new SeatRowDTO(sr));
        }
        for (SeatRow sr: flight.getSeatsEconomy()) {
                this.seatsEconomy.add(new SeatRowDTO(sr));
        }
        for (SeatRow sr: flight.getSeatsBusiness()) {
                this.seatsBusiness.add(new SeatRowDTO(sr));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirlineCompanyDTO getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompanyDTO airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public AirplaneDTO getAirplane() {
        return airplane;
    }

    public void setAirplane(AirplaneDTO airplane) {
        this.airplane = airplane;
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

    public double getTicketPriceBusiness() {
        return ticketPriceBusiness;
    }

    public void setTicketPriceBusiness(double ticketPriceBusiness) {
        this.ticketPriceBusiness = ticketPriceBusiness;
    }

    public double getTicketPriceEconomy() {
        return ticketPriceEconomy;
    }

    public void setTicketPriceEconomy(double ticketPriceEconomy) {
        this.ticketPriceEconomy = ticketPriceEconomy;
    }

    public List<SeatRowDTO> getSeatsFirst() {
        return seatsFirst;
    }

    public void setSeatsFirst(List<SeatRowDTO> seatsFirst) {
        this.seatsFirst = seatsFirst;
    }

    public List<SeatRowDTO> getSeatsBusiness() {
        return seatsBusiness;
    }

    public void setSeatsBusiness(List<SeatRowDTO> seatsBusiness) {
        this.seatsBusiness = seatsBusiness;
    }

    public List<SeatRowDTO> getSeatsEconomy() {
        return seatsEconomy;
    }

    public void setSeatsEconomy(List<SeatRowDTO> seatsEconomy) {
        this.seatsEconomy = seatsEconomy;
    }


    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
