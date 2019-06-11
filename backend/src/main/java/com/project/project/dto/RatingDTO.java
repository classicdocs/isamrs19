package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class RatingDTO {

    @NotNull
    private Long id;

    @NotNull
    private double service;

    @NotNull
    private double specific;

    public RatingDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getService() {
        return service;
    }

    public void setService(double service) {
        this.service = service;
    }

    public double getSpecific() {
        return specific;
    }

    public void setSpecific(double specific) {
        this.specific = specific;
    }
}
