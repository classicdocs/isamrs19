package com.project.project.model;

import com.project.project.dto.UserRegistrationDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passenger_id")
    private Long passengerId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String passport;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean accepted;

    @ManyToMany
    private Set<Luggage> luggages = new HashSet<>();

    @ManyToOne
    private FlightReservation flightReservation;

    public Passenger() {

    }

    public Passenger(UserRegistrationDTO passenger) {
        this.passengerId = passenger.getId();
        this.username = passenger.getUsername();
        this.firstname = passenger.getFirstname();
        this.lastname = passenger.getLastname();
        this.passport = passenger.getPassport();
        this.email = passenger.getEmail();
        this.phone = passenger.getPhone();
        this.address = passenger.getAddress();
        this.accepted = passenger.getId() == null;
    }

    public Set<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(Set<Luggage> luggages) {
        this.luggages = luggages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public FlightReservation getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservation flightReservation) {
        this.flightReservation = flightReservation;
    }
}
