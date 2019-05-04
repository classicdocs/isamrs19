package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class NewDestination {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String country;
    @NotNull
    private String airport;
    @NotNull
    private String zip;

    public NewDestination() {

    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
