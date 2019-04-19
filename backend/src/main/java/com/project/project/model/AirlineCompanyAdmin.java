package com.project.project.model;

import javax.persistence.*;

@Entity
public class AirlineCompanyAdmin extends User{

    @ManyToOne
    private AirlineCompany airlineCompany;

    public AirlineCompanyAdmin() {
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
}
