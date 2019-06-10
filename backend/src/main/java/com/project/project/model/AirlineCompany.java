package com.project.project.model;

import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class AirlineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "airline_company_destinations", joinColumns = @JoinColumn(name = "airline_company_id"))
    @Column(name = "destinations")
    private Set<String> destinations = new HashSet<String>();

    @OneToMany(mappedBy = "airlineCompany", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Flight> flights;

    @OneToMany(mappedBy = "airlineCompany", fetch = FetchType.EAGER)
    private Set<Airplane> airplanes;

    @OneToMany(mappedBy = "airlineCompany", fetch = FetchType.EAGER)
    private Set<FlightReservation> reservations = new HashSet<>();

    @OneToMany(mappedBy = "airlineCompany", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AirlineCompanyAdmin> admins;

    public AirlineCompany() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(Set<String> destinations) {
        this.destinations = destinations;
    }

    public Set<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(Set<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    public Set<FlightReservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<FlightReservation> reservations) {
        this.reservations = reservations;
    }

    public Set<AirlineCompanyAdmin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<AirlineCompanyAdmin> admins) {
        this.admins = admins;
    }


}
