package com.project.project.model;

import javax.persistence.*;

@Entity
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String dimensions;

    private int weight;

    private double price;

    @ManyToOne
    private AirlineCompany airlineCompany;

    public Luggage() {}

    public Luggage(Long id, String name, String dimensions, int weight, double price, AirlineCompany airlineCompany) {
        this.id = id;
        this.name = name;
        this.dimensions = dimensions;
        this.weight = weight;
        this.price = price;
        this.airlineCompany = airlineCompany;
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

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }
}
