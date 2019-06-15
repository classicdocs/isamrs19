package com.project.project.dto;

import com.project.project.model.AirlineCompany;
import com.project.project.model.MapLocation;

import javax.validation.constraints.NotNull;

public class AirlineCompanyDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    private String description;

    private double averageRating;
    private double totalRating;

    private MapLocation mapLocation;

    public AirlineCompanyDTO() {}

    public AirlineCompanyDTO(AirlineCompany airlineCompany) {
        this.id = airlineCompany.getId();
        this.name = airlineCompany.getName();
        this.address = airlineCompany.getAddress();
        this.description = airlineCompany.getDescription();
        this.averageRating = airlineCompany.getAverageRating();
        this.totalRating = airlineCompany.getTotalRating();
        this.mapLocation = airlineCompany.getMapLocation();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(double totalRating) {
        this.totalRating = totalRating;
    }

    public MapLocation getMapLocation() {
        return mapLocation;
    }

    public void setMapLocation(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }
}
