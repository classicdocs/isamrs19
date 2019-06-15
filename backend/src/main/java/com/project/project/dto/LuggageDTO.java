package com.project.project.dto;

import com.project.project.model.Luggage;

public class LuggageDTO {

    private Long id;

    private String name;

    private String dimensions;

    private int weight;

    private double price;

    public LuggageDTO() {}

    public LuggageDTO(Luggage l) {
        this.id = l.getId();
        this.name = l.getName();
        this.dimensions = l.getDimensions();
        this.weight = l.getWeight();
        this.price = l.getPrice();
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
}
