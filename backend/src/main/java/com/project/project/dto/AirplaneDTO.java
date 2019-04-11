package com.project.project.dto;

import javax.validation.constraints.NotNull;

public class AirplaneDTO {
    @NotNull
    private Long id;
    @NotNull
    private String model;

    public AirplaneDTO() {

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
}
