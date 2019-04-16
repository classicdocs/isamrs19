package com.project.project.dto;

import com.project.project.model.Airplane;

import javax.validation.constraints.NotNull;

public class AirplaneDTO {

    private Long id;
    @NotNull
    private String model;
    @NotNull
    private int seatsFirstRows;
    @NotNull
    private int seatsFirstCols;
    @NotNull
    private int seatsBusinessRows;
    @NotNull
    private int seatsBusinessCols;
    @NotNull
    private int seatsEconomyRows;
    @NotNull
    private int seatsEconomyCols;

    public AirplaneDTO() {

    }

    public AirplaneDTO(Airplane airplane) {
        this.id = airplane.getId();
        this.model = airplane.getModel();
        this.seatsFirstRows = airplane.getSeatsFirstRows();
        this.seatsFirstCols = airplane.getSeatsFirstCols();
        this.seatsBusinessRows = airplane.getSeatsBusinessRows();
        this.seatsBusinessCols = airplane.getSeatsBusinessCols();
        this.seatsEconomyRows = airplane.getSeatsEconomyRows();
        this.seatsEconomyCols = airplane.getSeatsEconomyCols();
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
}
