package com.project.project.model;

public class Destination {
    private String name;
    private String country;
    private String airport;
    private String zip;

    public Destination() {
    }

    public Destination(String name, String country, String airport, String zip) {
        this.name = name;
        this.country = country;
        this.airport = airport;
        this.zip = zip;
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
