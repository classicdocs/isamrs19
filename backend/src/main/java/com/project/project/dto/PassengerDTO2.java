package com.project.project.dto;

import com.project.project.model.Passenger;

public class PassengerDTO2 {

    private Long id;

    private Long passengerId;

    private String username;

    private String firstname;

    private String lastname;

    private String passport;

    private String email;

    private String phone;

    private String address;

    private boolean accepted;

    public PassengerDTO2() {}

    public PassengerDTO2(Passenger passenger) {
        id = passenger.getId();
        passengerId = passenger.getPassengerId();
        username = passenger.getUsername();
        firstname = passenger.getFirstname();
        lastname = passenger.getLastname();
        passport = passenger.getPassport();
        email = passenger.getEmail();
        phone = passenger.getPhone();
        address = passenger.getAddress();
        accepted = passenger.isAccepted();
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
}
