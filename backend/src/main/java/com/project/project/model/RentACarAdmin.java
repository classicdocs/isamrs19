package com.project.project.model;

import javax.persistence.*;

@Entity
public class RentACarAdmin {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RentACar rentACar;

    public RentACarAdmin() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RentACar getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACar rentACar) {
        this.rentACar = rentACar;
    }
}
