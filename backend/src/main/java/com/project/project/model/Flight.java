package com.project.project.model;


import org.hibernate.action.internal.OrphanRemovalAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AirlineCompany airlineCompany;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.EAGER)
    private Destination startDestination;

    @ManyToOne(fetch = FetchType.EAGER)
    private Destination finalDestination;

    @Column(name = "departure_date", nullable = false)
    private String departureDate;

    @Column(name = "departure_time", nullable = false)
    private String departureTime;

    @Column(name = "landing_date", nullable = false)
    private String landingDate;

    @Column(name = "landing_time", nullable = false)
    private String landingTime;

    @Column(name = "flight_time_hours", nullable = false)
    private int flightTimeHours;

    @Column(name = "flight_time_minutes", nullable = false)
    private int flightTimeMinutes;

    @Column(name = "distance", nullable = false)
    private double distance;

    @ElementCollection
    @CollectionTable(name = "flight_transfers", joinColumns = @JoinColumn(name = "flight_id"))
    @Column(name = "transfers")
    private Set<String> transferDestinations = new HashSet<>();

    @Column(name = "ticket_price_first", nullable = false)
    private double ticketPriceFirst;

    @Column(name = "ticket_price_business", nullable = false)
    private double ticketPriceBusiness;

    @Column(name = "ticket_price_economy", nullable = false)
    private double ticketPriceEconomy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Seat> seatsFirst = new HashSet<Seat>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Seat> seatsBusiness = new HashSet<Seat>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Seat> seatsEconomy = new HashSet<Seat>();

    public Flight() {
    }

    public int getFreeFirstSeats() {
        int cnt = this.seatsFirst.size();
        for (Seat s: this.seatsFirst) {
            if (s.isTaken()) {
                cnt--;
            }
        }
        return cnt;
    }
    public int getFreeBusinessSeats() {
        int cnt = this.seatsBusiness.size();
        for (Seat s: this.seatsBusiness) {
            if (s.isTaken()) {
                cnt--;
            }
        }
        return cnt;
    }
    public int getFreeEconomySeats() {
        int cnt = this.seatsEconomy.size();
        for (Seat s: this.seatsEconomy) {
            if (s.isTaken()) {
                cnt--;
            }
        }
        return cnt;
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

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
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

    public void setTicketPriceBusiness(double ticketPriceBuisness) {
        this.ticketPriceBusiness = ticketPriceBuisness;
    }

    public double getTicketPriceEconomy() {
        return ticketPriceEconomy;
    }

    public void setTicketPriceEconomy(double ticketPriceEconomy) {
        this.ticketPriceEconomy = ticketPriceEconomy;
    }

    public Set<Seat> getSeatsFirst() {
        return seatsFirst;
    }

    public void setSeatsFirst(Set<Seat> seatsFirst) {
        this.seatsFirst = seatsFirst;
    }

    public Set<Seat> getSeatsBusiness() {
        return seatsBusiness;
    }

    public void setSeatsBusiness(Set<Seat> seatsBusiness) {
        this.seatsBusiness = seatsBusiness;
    }

    public Set<Seat> getSeatsEconomy() {
        return seatsEconomy;
    }

    public void setSeatsEconomy(Set<Seat> seatsEconomy) {
        this.seatsEconomy = seatsEconomy;
    }
}

