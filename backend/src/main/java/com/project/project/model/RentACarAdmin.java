package com.project.project.model;

import javax.persistence.*;

@Entity
public class RentACarAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RentACar rentACar;

    public RentACarAdmin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long ID) {
        this.id = ID;
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
