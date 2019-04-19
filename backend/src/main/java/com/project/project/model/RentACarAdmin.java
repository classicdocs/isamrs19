package com.project.project.model;

import javax.persistence.*;

@Entity
public class RentACarAdmin extends User{

    @ManyToOne
    private RentACar rentACar;

    public RentACarAdmin() {
    }

    public RentACar getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACar rentACar) {
        this.rentACar = rentACar;
    }
}
