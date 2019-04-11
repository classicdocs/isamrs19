package com.project.project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="model", nullable = false)
    private String model;

    @Column(name = "seats_first_rows" ,nullable = false)
    private int seatsFirstRows;

    @Column(name = "seats_first_cols" ,nullable = false)
    private int seatsFirstCols;

    @Column(name = "seats_business_rows", nullable = false)
    private int seatsBusinessRows;

    @Column(name = "seats_business_cols", nullable = false)
    private int seatsBusinessCols;

    @Column(name = "seats_economy_rows", nullable = false)
    private int seatsEconomyRows;

    @Column(name = "seats_economy_cols", nullable = false)
    private int seatsEconomyCols;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Flight> flight;

    public Airplane() {
        this.flight = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeatsFirstRows() {
        return seatsFirstRows;
    }

    public void setSeatsFirstRows(int seatsFirstRows) {
        this.seatsFirstRows = seatsFirstRows;
    }

    public int getSeatsFirstCols() {
        return seatsFirstCols;
    }

    public void setSeatsFirstCols(int seatsFirstCols) {
        this.seatsFirstCols = seatsFirstCols;
    }

    public int getSeatsBusinessRows() {
        return seatsBusinessRows;
    }

    public void setSeatsBusinessRows(int seatsBusinessRows) {
        this.seatsBusinessRows = seatsBusinessRows;
    }

    public int getSeatsBusinessCols() {
        return seatsBusinessCols;
    }

    public void setSeatsBusinessCols(int seatsBusinessCols) {
        this.seatsBusinessCols = seatsBusinessCols;
    }

    public int getSeatsEconomyRows() {
        return seatsEconomyRows;
    }

    public void setSeatsEconomyRows(int seatsEconomyRows) {
        this.seatsEconomyRows = seatsEconomyRows;
    }

    public int getSeatsEconomyCols() {
        return seatsEconomyCols;
    }

    public void setSeatsEconomyCols(int seatsEconomyCols) {
        this.seatsEconomyCols = seatsEconomyCols;
    }

    public Set<Flight> getFlight() {
        return flight;
    }

    public void setFlight(Set<Flight> flight) {
        this.flight = flight;
    }


}
